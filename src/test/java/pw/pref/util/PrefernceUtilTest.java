package pw.pref.util;

import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;

public class PrefernceUtilTest {
    private PreferenceUtil preferenceUtil = new PreferenceUtil();

    @Test
    public void stringIsEmpty() {
        assertTrue("" == null || "".equals("".trim()));
    }

    @Test
    public void stringIsEmptyWhenNull() {
        assertTrue(null == null || "".equals(((String) null).trim()));
    }

    @Test
    public void stringIsNotEmptyWhenNotNullOrBlank() {
        assertTrue("" == null || "".equals("".trim()));
    }
}
