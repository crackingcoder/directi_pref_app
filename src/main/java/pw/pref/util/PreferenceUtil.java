package pw.pref.util;

import org.springframework.stereotype.Component;

@Component
public class PreferenceUtil {
    public boolean isNullOrEmpty(String str) {
        return (str == null || "".equals(str.trim())) ? true : false;
    }

}
