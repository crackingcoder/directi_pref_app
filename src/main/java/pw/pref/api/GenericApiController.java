package pw.pref.api;

import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pw.pref.api.helper.ApiContext;
import pw.pref.api.helper.ApiResult;
import pw.pref.api.helper.JsonResponseGenerator;
import pw.pref.api.helper.ApiValidator;
import pw.pref.service.AuthService;
import pw.pref.service.PreferenceService;
import pw.pref.service.ChatService;
import pw.pref.exception.PreferenceException;

import java.io.IOException;
import java.util.List;
import java.util.Arrays;

public abstract class GenericApiController implements Controller {
    private AuthService authService;
    private JsonResponseGenerator jsonResponseGenerator;
    private ApiValidator apiValidator;
    protected PreferenceService preferenceService;
    protected ChatService chatService;

    public ModelAndView handleRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws Exception {
        ApiContext apiContext = new ApiContext(httpServletRequest);
        try {
            authService.authenticate(apiContext);
            markOptionalFields(apiContext);
            apiValidator.validate(apiContext, getSupportedMethods());
            action(apiContext);
        } catch (Exception e) {
            apiContext.setFailureStatus(e.getMessage());
        }
        writeResponse(httpServletResponse, apiContext);
        return null;
    }

    protected void markOptionalFields(ApiContext apiContext) {
        apiContext.setUserIdOptional(false);
        apiContext.setKeyOptional(false);
    }

    abstract protected void action(ApiContext apiContext) throws PreferenceException;

    protected List<String> getSupportedMethods() {
        return Arrays.asList("GET", "POST");
    }

    private void writeResponse(HttpServletResponse httpServletResponse, ApiContext apiContext) throws IOException {
        ApiResult apiResult = apiContext.getResult();
        jsonResponseGenerator.generateResponse(httpServletResponse, apiResult);
    }

    @Autowired
    public void setAuthService(AuthService authService) {
        this.authService = authService;
    }

    @Autowired
    public void setJsonResponseGenerator(JsonResponseGenerator jsonResponseGenerator) {
        this.jsonResponseGenerator = jsonResponseGenerator;
    }

    @Autowired
    public void setApiValidator(ApiValidator apiValidator) {
        this.apiValidator = apiValidator;
    }

    @Autowired
    public void setPreferenceService(PreferenceService preferenceService) {
        this.preferenceService = preferenceService;
    }

    @Autowired
    public void setChatService(ChatService chatService) {
        this.chatService = chatService;
    }
}
