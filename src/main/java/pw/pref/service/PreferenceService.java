package pw.pref.service;

import pw.pref.dao.PreferenceDao;
import pw.pref.exception.PreferenceException;
import pw.pref.util.PreferenceUtil;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class PreferenceService {
    final private PreferenceUtil preferenceUtil;
    final private PreferenceDao preferenceDao;

    @Autowired
    public PreferenceService(PreferenceDao preferenceDao, PreferenceUtil preferenceUtil) {
        this.preferenceDao = preferenceDao;
        this.preferenceUtil = preferenceUtil;
    }

    public void setPreference(String key, String value, String appId, String userId) throws PreferenceException {
        validateParameters(key, appId, userId);
        preferenceDao.save(key, value, appId, userId);
    }

//    public void getPreference(String key, String appId, String userId) {
//        preferenceDao.findByKey(key, appId, userId);
//    }
//
//    public void getAllPreferences(String appId) {
//        preferenceDao.findByAppId(appId);
//    }

    private void validateParameters(String ... params) throws PreferenceException {
        for (String param : params) {
            if (preferenceUtil.isNullOrEmpty(param)) {
                throw new PreferenceException();
            }
        }
    }
}
