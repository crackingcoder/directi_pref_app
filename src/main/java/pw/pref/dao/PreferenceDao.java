package pw.pref.dao;

import com.ibatis.sqlmap.client.SqlMapClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Component;
import pw.pref.model.Preference;
import pw.pref.util.PreferenceUtil;

import java.util.Map;
import java.util.List;

@Component
public class PreferenceDao extends SqlMapClientDaoSupport {

    @Autowired
    public PreferenceDao(SqlMapClient sqlMapClient, PreferenceUtil preferenceUtil) {
        setSqlMapClient(sqlMapClient);
    }

    public void save(Preference preference) {
        Preference savedPreference = find(preference);
        if (savedPreference == null) {
            getSqlMapClientTemplate().insert("Preference.insert", preference);
        } else {
            getSqlMapClientTemplate().update("Preference.update", preference);
        }
    }

    public Preference find(Preference preference) {
        return (Preference) getSqlMapClientTemplate().queryForObject("Preference.find", preference);
    }

    public List<Preference> findByAppId(Preference preference) {
        return (List<Preference>) getSqlMapClientTemplate()
                .queryForList("Preference.findByAppId", preference);
    }
}
