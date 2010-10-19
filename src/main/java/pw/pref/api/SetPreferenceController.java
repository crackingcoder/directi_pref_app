package pw.pref.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import pw.pref.api.helper.ApiContext;
import pw.pref.api.helper.ApiResult;
import pw.pref.api.helper.ApiValidator;
import pw.pref.api.helper.JsonResponseGenerator;
import pw.pref.exception.PreferenceException;
import pw.pref.service.AuthService;
import pw.pref.service.PreferenceService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@org.springframework.stereotype.Controller
public class SetPreferenceController implements Controller {
    private final PreferenceService preferenceService;
    private final AuthService authService;
    private final JsonResponseGenerator jsonResponseGenerator;
    private ApiValidator apiValidator;

    @Autowired
    public SetPreferenceController(
            PreferenceService preferenceService, AuthService authService, JsonResponseGenerator jsonResponseGenerator,
            ApiValidator apiValidator
    ) {
        this.preferenceService = preferenceService;
        this.authService = authService;
        this.jsonResponseGenerator = jsonResponseGenerator;
        this.apiValidator = apiValidator;
    }

    public ModelAndView handleRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws Exception {
        ApiContext apiContext = new ApiContext(httpServletRequest);
        try {
            authService.authenticate(apiContext);
            apiValidator.validate(apiContext, getSupportedMethods());
            action(apiContext);
        } catch (PreferenceException pe) {
            apiContext.setFailureStatus(pe.getMessage());
        }
        writeResponse(httpServletResponse, apiContext);
        return null;
    }

    private void action(ApiContext apiContext) throws PreferenceException {
        preferenceService.setPreference(apiContext.getPreference());
        apiContext.setSuccessStatus();
    }

    private void writeResponse(HttpServletResponse httpServletResponse, ApiContext apiContext) throws IOException {
        ApiResult apiResult = apiContext.getResult();
        jsonResponseGenerator.generateResponse(httpServletResponse, apiResult);
    }

    private List getSupportedMethods() {
        return Arrays.asList("POST");
    }
}
