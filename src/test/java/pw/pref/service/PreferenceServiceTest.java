package pw.pref.service;

import org.mockito.Matchers;
import static org.mockito.Mockito.*;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pw.pref.dao.PreferenceDao;
import pw.pref.exception.PreferenceException;
import pw.pref.util.PreferenceUtil;

public class PreferenceServiceTest {

    private PreferenceService preferenceService;
    private PreferenceDao preferenceDao;
    private PreferenceUtil preferenceUtil;

    private static final String KEY = "test_key";
    private static final String VALUE = null;
    private static final String APP_ID = "app_id";
    private static final String USER_ID = "12345";

    @BeforeTest
    public void setPreferenceService() {
        this.preferenceDao = mock(PreferenceDao.class);
        this.preferenceUtil = mock(PreferenceUtil.class);
        this.preferenceService = new PreferenceService(preferenceDao, preferenceUtil);
        when(preferenceUtil.isNullOrEmpty(null)).thenReturn(true);
        when(preferenceUtil.isNullOrEmpty((String) Matchers.isNotNull())).thenReturn(false);
    }

    @Test
    public void setPreference() throws PreferenceException {
        preferenceService.setPreference(KEY, VALUE, APP_ID, USER_ID);
        verify(preferenceDao).save(KEY, VALUE, APP_ID, USER_ID);
    }

    @Test(expectedExceptions = PreferenceException.class)
    public void setPreferenceWithNullKey() throws PreferenceException {
        preferenceService.setPreference(null, VALUE, APP_ID, USER_ID);
    }

    @Test(expectedExceptions = PreferenceException.class)
    public void setPreferenceWithNullAppId() throws PreferenceException {
        preferenceService.setPreference(KEY, VALUE, null, USER_ID);
    }

    @Test(expectedExceptions = PreferenceException.class)
    public void setPreferenceWithNullUserId() throws PreferenceException {
        preferenceService.setPreference(KEY, VALUE, APP_ID, null);
    }

//    @Test (enabled = false)
//    public void getPreference() {
//        preferenceService.getPreference(KEY, APP_ID, USER_ID);
//        verify(preferenceDao).findByKey(KEY, APP_ID, USER_ID);
//    }
//
//    @Test (enabled = false)
//    public void getAllPreferences() {
//        preferenceService.getAllPreferences(APP_ID);
//        verify(preferenceDao).findByAppId(APP_ID);
//    }
}
