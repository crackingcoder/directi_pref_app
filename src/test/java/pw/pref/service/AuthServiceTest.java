package pw.pref.service;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pw.authentication.model.AuthenticatedUser;
import pw.authentication.web.api.http.client.ServiceResponse;
import pw.authentication.api.client.RestServiceClient;
import pw.pref.api.helper.ApiContext;
import pw.pref.constants.ApiConstants;
import pw.pref.exception.PreferenceException;
import pw.pref.util.PreferenceUtil;

import java.util.UUID;

public class AuthServiceTest {
    AuthService authService;
    private RestServiceClient restServiceClient;
    private PreferenceUtil preferenceUtil;
    private ApiContext apiContext;
    private ServiceResponse serviceResponse;
    private AuthenticatedUser authenticatedUser;

    @BeforeMethod
    public void setUp() {
        restServiceClient = mock(RestServiceClient.class);
        preferenceUtil = new PreferenceUtil();
        apiContext = mock(ApiContext.class);
        serviceResponse = mock(ServiceResponse.class);
        authenticatedUser = mock(AuthenticatedUser.class);
        authService = new AuthService(preferenceUtil, restServiceClient);
    }

    @Test
    public void whenAuthTokenIsValid() throws PreferenceException {
        when(apiContext.getAuthToken()).thenReturn("someToken");
        when(restServiceClient.callRestApi(anyString(), anyString(), anyString(), anyString()))
                .thenReturn(serviceResponse);
        when(serviceResponse.hasError()).thenReturn(false);
        when(serviceResponse.getData()).thenReturn(authenticatedUser);
        UUID uuid = UUID.randomUUID();
        when(authenticatedUser.getUuid()).thenReturn(uuid);
        authService.authenticate(apiContext);
        verify(apiContext).setUserId(uuid.toString());
    }

    @Test(expectedExceptions = PreferenceException.class)
    public void whenAuthTokenIsInvalid() throws PreferenceException {
        when(apiContext.getAuthToken()).thenReturn("someToken");
        when(restServiceClient.callRestApi(anyString(), anyString(), anyString(), anyString()))
                .thenReturn(serviceResponse);
        when(serviceResponse.hasError()).thenReturn(true);
//        when(serviceResponse.getData()).thenReturn(authenticatedUser);
//        UUID uuid = UUID.randomUUID();
//        when(authenticatedUser.getUuid()).thenReturn(uuid);
        authService.authenticate(apiContext);
//        verify(apiContext).setUserId(uuid.toString());
    }

    @Test
    public void whenValidAppkeyAndUserIdArePresent() throws PreferenceException {
        when(apiContext.getAuthToken()).thenReturn(null);
        when(apiContext.getAppKey()).thenReturn(ApiConstants.PREF_APP_KEY);
        when(restServiceClient.callRestApi(anyString(), anyString(), anyString(), anyString()))
                .thenReturn(serviceResponse);
        when(serviceResponse.hasError()).thenReturn(false);
        authService.authenticate(apiContext);
        verify(restServiceClient).callRestApi(anyString(), anyString(), anyString(), anyString());
    }

    @Test (expectedExceptions = PreferenceException.class)
    public void whenValidAppkeyIsInvalid() throws PreferenceException {
        when(apiContext.getAuthToken()).thenReturn(null);
        when(apiContext.getAppKey()).thenReturn("somekey");
        authService.authenticate(apiContext);
    }
}
