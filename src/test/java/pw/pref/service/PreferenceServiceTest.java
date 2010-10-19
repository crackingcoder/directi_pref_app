package pw.pref.service;

import static org.mockito.Mockito.*;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import pw.pref.dao.PreferenceDao;
import pw.pref.exception.PreferenceException;
import pw.pref.util.PreferenceUtil;
import pw.pref.api.helper.ApiContext;
import pw.pref.model.Preference;

public class PreferenceServiceTest {

    private PreferenceService preferenceService;
    private PreferenceDao preferenceDao;
    private Preference preference;

    @BeforeMethod
    public void setPreferenceService() {
        this.preferenceDao = mock(PreferenceDao.class);
        this.preference = mock(Preference.class);
        this.preferenceService = new PreferenceService(preferenceDao);
    }

    @Test
    public void setPreference() throws PreferenceException {
        preferenceService.setPreference(preference);
        verify(preferenceDao).save(preference);
    }
}
