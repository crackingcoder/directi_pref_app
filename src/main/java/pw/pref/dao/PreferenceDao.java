package pw.pref.dao;

import com.ibatis.sqlmap.client.SqlMapClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Component;
import pw.pref.model.Preference;
import pw.pref.util.PreferenceUtil;

import java.util.Map;

@Component
public class PreferenceDao extends SqlMapClientDaoSupport {

    private PreferenceUtil preferenceUtil;

    @Autowired
    public PreferenceDao(SqlMapClient sqlMapClient, PreferenceUtil preferenceUtil) {
        this.preferenceUtil = preferenceUtil;
        setSqlMapClient(sqlMapClient);
    }

    public void save(Preference preference) {
        if (preferenceUtil.anyOneIsNullOrEmpty(find(preference))) {
            getSqlMapClientTemplate().insert("Preference.insert", preference);
        } else {
            getSqlMapClientTemplate().update("Preference.update", preference);
        }
    }

    public String find(Preference preference) {
        return (String) getSqlMapClientTemplate().queryForObject("Preference.find", preference);
    }

    public Map<String, String> findByAppId(Preference preference) {
        return (Map<String, String>) getSqlMapClientTemplate()
                .queryForMap("Preference.findByAppId", preference, "key", "value");
    }
}
