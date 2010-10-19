package pw.pref.util;

import org.springframework.stereotype.Component;

@Component
public class PreferenceUtil {
    public boolean anyOneIsNullOrEmpty(String... strngs) {
        for (String str : strngs) {
            if (str == null || "".equals(str.trim()))
                return true;
        }
        return false;
    }

}
