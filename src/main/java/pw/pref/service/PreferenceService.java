package pw.pref.service;

import pw.pref.dao.PreferenceDao;
import pw.pref.exception.PreferenceException;
import pw.pref.util.PrefernceUtil;

public class PreferenceService {
    final private PrefernceUtil preferenceUtil;
    final private PreferenceDao preferenceDao;

    public PreferenceService(PreferenceDao preferenceDao, PrefernceUtil preferenceUtil) {
        this.preferenceDao = preferenceDao;
        this.preferenceUtil = preferenceUtil;
    }

    public void setPreference(String key, String value, String appId, String source, String userId) throws PreferenceException {
        validateParameters(key, appId, source, userId);
        preferenceDao.save(key, value, appId, source, userId);
    }

    public void getPreference(String key, String appId, String userId) {
        preferenceDao.findByKey(key, appId, userId);
    }

    public void getAllPreferences(String appId) {
        preferenceDao.findByAppId(appId);
    }

    private void validateParameters(String ... params) throws PreferenceException {
        for (String param : params) {
            if (preferenceUtil.isNullOrEmpty(param)) {
                throw new PreferenceException();
            }
        }
    }
}
