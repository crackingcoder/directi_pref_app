package pw.pref.util;

import org.testng.annotations.Test;
import org.testng.Assert;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertFalse;

public class PreferenceUtilTest {
    @Test
    public void whenNull() {
        PreferenceUtil preferenceUtil = new PreferenceUtil();
        assertTrue(preferenceUtil.isNullOrEmpty(null));
    }

    @Test
    public void whenNotNull() {
        PreferenceUtil preferenceUtil = new PreferenceUtil();
        assertFalse(preferenceUtil.isNullOrEmpty("Heloo"));
    }

        @Test
    public void whenEmpty() {
        PreferenceUtil preferenceUtil = new PreferenceUtil();
        assertTrue(preferenceUtil.isNullOrEmpty(""));
    }
}
