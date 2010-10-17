package pw.pref.service;

import pw.pref.exception.PreferenceException;
import pw.pref.util.PreferenceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private PreferenceUtil preferenceUtil;

    @Autowired
    public AuthService(PreferenceUtil preferenceUtil) {
        this.preferenceUtil = preferenceUtil;
    }

    public String authenticateToken(String authToken) throws PreferenceException {
        if (preferenceUtil.isNullOrEmpty(authToken))
            throw new PreferenceException();
        else
            return "8732874832";
    }
}
