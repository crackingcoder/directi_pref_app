package pw.pref.service;

import org.mockito.Matchers;
import static org.mockito.Mockito.*;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pw.pref.dao.PreferenceDao;
import pw.pref.exception.PreferenceException;
import pw.pref.util.PrefernceUtil;

public class PreferenceServiceTest {

    private PreferenceService preferenceService;
    private PreferenceDao preferenceDao;
    private PrefernceUtil prefernceUtil;

    private static final String KEY = "test_key";
    private static final String VALUE = null;
    private static final String APP_ID = "app_id";
    private static final String SOURCE = "C";
    private static final String USER_ID = "12345";

    @BeforeTest
    public void setPreferenceService() {
        this.preferenceDao = mock(PreferenceDao.class);
        this.prefernceUtil = mock(PrefernceUtil.class);
        this.preferenceService = new PreferenceService(preferenceDao, prefernceUtil);
        when(prefernceUtil.isNullOrEmpty(null)).thenReturn(true);
        when(prefernceUtil.isNullOrEmpty((String) Matchers.isNotNull())).thenReturn(false);
    }

    @Test
    public void setPreference() throws PreferenceException {
        preferenceService.setPreference(KEY, VALUE, APP_ID, SOURCE, USER_ID);
        verify(preferenceDao).save(KEY, VALUE, APP_ID, SOURCE, USER_ID);
    }

    @Test(expectedExceptions = PreferenceException.class)
    public void setPreferenceWithNullKey() throws PreferenceException {
        preferenceService.setPreference(null, VALUE, APP_ID, SOURCE, USER_ID);
    }

    @Test(expectedExceptions = PreferenceException.class)
    public void setPreferenceWithNullAppId() throws PreferenceException {
        preferenceService.setPreference(KEY, VALUE, null, SOURCE, USER_ID);
    }

    @Test(expectedExceptions = PreferenceException.class)
    public void setPreferenceWithNullSource() throws PreferenceException {
        preferenceService.setPreference(KEY, VALUE, APP_ID, SOURCE, null);
    }

    @Test(expectedExceptions = PreferenceException.class)
    public void setPreferenceWithNullUserId() throws PreferenceException {
        preferenceService.setPreference(KEY, VALUE, APP_ID, null, USER_ID);
    }

    @Test
    public void getPreference() {
        preferenceService.getPreference(KEY, APP_ID, USER_ID);
        verify(preferenceDao).findByKey(KEY, APP_ID, USER_ID);
    }

    @Test
    public void getAllPreferences() {
        preferenceService.getAllPreferences(APP_ID);
        verify(preferenceDao).findByAppId(APP_ID);
    }
}
