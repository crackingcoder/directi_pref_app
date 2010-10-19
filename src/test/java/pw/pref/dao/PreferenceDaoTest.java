package pw.pref.dao;

import pw.pref.util.PreferenceUtil;
import pw.pref.model.Preference;
import com.ibatis.sqlmap.client.SqlMapClient;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import org.springframework.orm.ibatis.SqlMapClientTemplate;

import java.sql.SQLException;

public class PreferenceDaoTest {

    private SqlMapClient sqlMapClient;
    private PreferenceUtil preferenceUtil;
    PreferenceDao preferenceDao;
    private Preference preference;
    private SqlMapClientTemplate sqlMapClientTemplate;

    @BeforeMethod()
    public void setUp() {
        sqlMapClient = mock(SqlMapClient.class);
        preferenceUtil = new PreferenceUtil();
        preferenceDao = new PreferenceDao(sqlMapClient, preferenceUtil);
        sqlMapClientTemplate = mock(SqlMapClientTemplate.class);
        preferenceDao.setSqlMapClientTemplate(sqlMapClientTemplate);
        preference = mock(Preference.class);
    }

    @Test
    public void insertsWhenNotExists() throws SQLException {
        when(sqlMapClientTemplate.queryForObject("Preference.find", preference)).thenReturn(null);
        preferenceDao.save(preference);
        verify(sqlMapClientTemplate).insert("Preference.insert", preference);
    }

    @Test
    public void updatesWhenExists() throws SQLException {
        when(sqlMapClientTemplate.queryForObject("Preference.find", preference)).thenReturn("some_value");
        preferenceDao.save(preference);
        verify(sqlMapClientTemplate).update("Preference.update", preference);
    }
}
