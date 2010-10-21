package pw.pref.api;

import pw.pref.api.helper.ApiContext;
import pw.pref.exception.PreferenceException;
import pw.pref.model.Preference;
import org.springframework.stereotype.Controller;

@Controller
public class GetPreferenceController extends GenericApiController {
    @Override
    protected void action(ApiContext apiContext) throws PreferenceException {
        Preference savedPreference = preferenceService.getPreference(apiContext.getPreference());
        apiContext.setData(savedPreference);
        apiContext.setSuccessStatus();
    }
}
