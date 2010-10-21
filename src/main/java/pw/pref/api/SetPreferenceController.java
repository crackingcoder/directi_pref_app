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
public class SetPreferenceController extends GenericApiController {

    @Override
    protected void action(ApiContext apiContext) throws PreferenceException {
        preferenceService.setPreference(apiContext.getPreference());
        apiContext.setSuccessStatus();
    }

    @Override
    protected List getSupportedMethods() {
        return Arrays.asList("POST");
    }
}
