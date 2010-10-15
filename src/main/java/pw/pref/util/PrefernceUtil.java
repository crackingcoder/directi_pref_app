package pw.pref.util;

public class PrefernceUtil {
    public boolean isNullOrEmpty(String str) {
        return str == null || "".equals(str.trim());
    }
}
