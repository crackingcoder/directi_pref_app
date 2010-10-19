package pw.pref.api.helper;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.argThat;
import pw.pref.util.PreferenceUtil;
import pw.pref.exception.PreferenceException;

import java.util.Arrays;
import java.util.List;

public class ApiValidatorTest {

    private ApiValidator apiValidator;
    private PreferenceUtil preferenceUtil;
    private ApiContext apiContext;

    @BeforeTest
    public void setUp() {
        this.preferenceUtil = new PreferenceUtil();
        this.apiContext = mock(ApiContext.class);
        this.apiValidator = new ApiValidator(preferenceUtil);
    }

    @Test (expectedExceptions = PreferenceException.class)
    public void httpMethodDoesNotMatch() throws PreferenceException {
        when(apiContext.getHttpMethod()).thenReturn("GET");
        apiValidator.validate(apiContext, Arrays.asList("POST"));
    }

    @Test (expectedExceptions = PreferenceException.class)
    public void appIdIsNull() throws PreferenceException {
        validateParameters(null, "some_key", "some_user_id");
    }

    @Test (expectedExceptions = PreferenceException.class)
    public void keyIsNull() throws PreferenceException {
        validateParameters("app_id", null, "some_user_id");
    }

    @Test (expectedExceptions = PreferenceException.class)
    public void userIdIsNull() throws PreferenceException {
        validateParameters("app_id", "some_key", null);
    }

    @Test
    public void allParamsPresent() throws PreferenceException {
        validateParameters("app_id", "some_key", "user_id");
    }

    private void validateParameters(String appId, String key, String userId) throws PreferenceException {
        when(apiContext.getHttpMethod()).thenReturn("POST");
        when(apiContext.getAppId()).thenReturn(appId);
        when(apiContext.getKey()).thenReturn(key);
        when(apiContext.getUserId()).thenReturn(userId);
        apiValidator.validate(apiContext, Arrays.asList("POST"));
    }
}
