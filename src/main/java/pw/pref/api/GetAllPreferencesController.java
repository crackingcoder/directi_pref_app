package pw.pref.api;

import pw.pref.api.GenericApiController;
import pw.pref.api.helper.ApiContext;
import pw.pref.exception.PreferenceException;
import pw.pref.model.Preference;

import java.util.List;

import org.springframework.stereotype.Controller;

@Controller
public class GetAllPreferencesController extends GenericApiController {
    @Override
    protected void action(ApiContext apiContext) throws PreferenceException {
        List<Preference> preferenceList = preferenceService.getAllPreferences(apiContext);
        apiContext.setData(preferenceList);
        apiContext.setSuccessStatus();
    }

    @Override
    protected void markOptionalFields(ApiContext apiContext) {
        apiContext.setUserIdOptional(true);
        apiContext.setKeyOptional(true);
    }
}
