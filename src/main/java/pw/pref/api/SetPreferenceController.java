package pw.pref.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import pw.pref.exception.PreferenceException;
import pw.pref.service.AuthService;
import pw.pref.service.PreferenceService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@org.springframework.stereotype.Controller
public class SetPreferenceController implements Controller {
    private PreferenceService preferenceService;
    private AuthService authService;

    @Autowired
    public SetPreferenceController(PreferenceService preferenceService, AuthService authService) {
        this.preferenceService = preferenceService;
        this.authService = authService;
//        setCommandClass(Object.class);
    }

    protected ModelAndView onSubmit(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, BindException e) throws Exception {
        String key = httpServletRequest.getParameter("key");
        String value = httpServletRequest.getParameter("value");
        String appId = httpServletRequest.getParameter("app_id");
        String authToken = httpServletRequest.getParameter("auth_token");
        try {
            String userId = authService.authenticateToken(authToken);
            action(key, value, appId, userId);
            return new ModelAndView("api-result", "status", "success");
        } catch (PreferenceException e1) {
            return new ModelAndView("api-result", "status", "failure");
        }
    }

    private void action(String key, String value, String appId, String userId) throws PreferenceException {
        preferenceService.setPreference(key, value, appId, userId);
    }

    public ModelAndView handleRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        String key = httpServletRequest.getParameter("key");
        String value = httpServletRequest.getParameter("value");
        String appId = httpServletRequest.getParameter("app_id");
        String authToken = httpServletRequest.getParameter("auth_token");
        String status = "";
        try {
            String userId = authService.authenticateToken(authToken);
            action(key, value, appId, userId);
            status = "success";
        } catch (PreferenceException e1) {
            httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            status = "failure";
        }
        httpServletResponse.setContentType("application/json; charset=utf-8");
        httpServletResponse.getWriter().write("{status:"+status+"}");
        return null;
    }
}
