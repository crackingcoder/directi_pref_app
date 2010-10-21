package pw.pref.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pw.pref.dao.PreferenceDao;
import pw.pref.exception.PreferenceException;
import pw.pref.model.Preference;
import pw.pref.api.helper.ApiContext;

import java.util.List;

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


    public Preference getPreference(Preference preference) {
        return preferenceDao.find(preference);
    }

    public List<Preference> getAllPreferences(ApiContext apiContext) {
        return preferenceDao.findByAppId(apiContext.getPreference());
    }
}
