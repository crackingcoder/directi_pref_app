package pw.pref.util;

import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;

public class PrefernceUtilTest {
    private PreferenceUtil preferenceUtil = new PreferenceUtil();

    @Test
    public void stringIsEmpty() {
        assertTrue(preferenceUtil.isNullOrEmpty(""));
    }

    @Test
    public void stringIsEmptyWhenNull() {
        assertTrue(preferenceUtil.isNullOrEmpty(null));
    }

    @Test
    public void stringIsNotEmptyWhenNotNullOrBlank() {
        assertTrue(preferenceUtil.isNullOrEmpty(""));
    }
}
