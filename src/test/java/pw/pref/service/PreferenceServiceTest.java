package pw.pref.service;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pw.pref.dao.PreferenceDao;
import pw.pref.exception.PreferenceException;
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

    @Test
    public void getPreference() throws PreferenceException {
        preferenceService.getPreference(preference);
        verify(preferenceDao).find(preference);
    }

    @Test
    public void getAllPreferences() throws PreferenceException {
        preferenceService.getAllPreferences(preference);
        verify(preferenceDao).findByAppId(preference);
    }
}
