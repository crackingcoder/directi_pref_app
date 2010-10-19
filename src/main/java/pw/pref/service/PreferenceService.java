package pw.pref.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pw.pref.dao.PreferenceDao;
import pw.pref.exception.PreferenceException;
import pw.pref.model.Preference;

@Service
public class PreferenceService {
    final private PreferenceDao preferenceDao;

    @Autowired
    public PreferenceService(PreferenceDao preferenceDao) {
        this.preferenceDao = preferenceDao;
    }

    public void setPreference(Preference preference) throws PreferenceException {
        preferenceDao.save(preference);
    }

//    public void getPreference(String key, String appId, String userId) {
//        preferenceDao.findByKey(key, appId, userId);
//    }
//
//    public void getAllPreferences(String appId) {
//        preferenceDao.findByAppId(appId);
//    }


}
