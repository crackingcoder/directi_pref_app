package pw.pref.service;

import org.springframework.stereotype.Service;
import pw.pref.api.helper.ApiContext;
import pw.pref.exception.PreferenceException;

@Service
public class AuthService {

    public void authenticate(ApiContext apiContext) throws PreferenceException {
        String str = apiContext.getAuthToken();
        if (str == null || "".equals(str.trim())) {
            throw new PreferenceException("Authentication failed");
        } else
            apiContext.setUserId("3182728");
    }
}
