package pw.pref.api;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pw.pref.service.PreferenceService;
import pw.pref.exception.PreferenceException;

public class SetPreferenceController extends SimpleFormController {
    private PreferenceService preferenceService;

    @Autowired
    public SetPreferenceController(PreferenceService preferenceService) {
        this.preferenceService = preferenceService;
    }

    @Override
    protected ModelAndView onSubmit(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, BindException e) throws Exception {
        try {
            preferenceService.setPreference(null, null, null, null, null);
        } catch (PreferenceException pe) {
                        
        }
        return null;
    }
}
