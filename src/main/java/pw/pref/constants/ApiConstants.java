package pw.pref.constants;

import org.springframework.stereotype.Component;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

@Component
public class ApiConstants {

    public static final String FAILURE = "failure";
    public static final String SUCCESS = "success";
    public static final String APPKEY = "appkey";
    public static final String AUTH_VALIDATE_TOKEN_CONTEXT = "/auth/validate_token.xml";
    public static final String AUTHTOKEN_PARAMETER = "authtoken";
    public static final String USER_BY_ID_CONTEXT = "/user/by_id.xml";
    public static final String NOTIFY_PREFERENCE_CHANGE_CONTEXT = getProperty("chat_preference_change_notify_context","/notifyPreferenceChange");

    public static final String USER_ID_PARAMETER = "id";
    public static final String USERAPP_KEY = getProperty("user.app.key", "testkey.pw");
    private static final String TLD = getProperty("TLD", ".pw");
    public static final String USER_APP_URL = getProperty("uapp.service.url", "http://user.api" + TLD);
    public static final String PREF_APP_KEY = getProperty("pref_app_key", "testkey.pw");
    public static final String CHATSERVER_URL = getProperty("chatserver_url", "chatserver.api.pw");
    public static final String KEY_PARAMETER = "key";
    public static final String VALUE_PARAMETER = "value";

    public static String getProperty(final String key, final String defaultVal) {

        final ResourceBundle resourceBundle = ResourceBundle.getBundle("application");
        String propertyValue;

        try {
            propertyValue = resourceBundle.getString(key);
        } catch (final MissingResourceException e) {
            return defaultVal;
        }

        return (!isEmpty(propertyValue)) ? propertyValue
                : defaultVal;
    }

    public static boolean isEmpty(final String str) {
        return str == null || str.trim().length() == 0 || str.trim().equalsIgnoreCase("null");
    }
}
