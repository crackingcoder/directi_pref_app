package pw.pref.api;

import pw.pref.api.helper.ApiContext;
import pw.pref.exception.PreferenceException;

import java.util.Arrays;
import java.util.List;

@org.springframework.stereotype.Controller
public class SetPreferenceController extends GenericApiController {

    @Override
    protected void action(ApiContext apiContext) throws PreferenceException {
        preferenceService.setPreference(apiContext.getPreference());
        if (apiContext.isNotifyChatServer()) {
            chatService.notifyChatOfNewValue(apiContext);
        }
        apiContext.setSuccessStatus();
    }

    @Override
    protected List getSupportedMethods() {
        return Arrays.asList("POST");
    }
}
