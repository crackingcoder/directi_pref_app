package pw.pref.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pw.pref.api.client.RestServiceClient;
import pw.authentication.web.api.http.client.ServiceResponse;
import pw.authentication.model.AuthenticatedUser;
import pw.pref.api.helper.ApiContext;
import pw.pref.constants.ApiConstants;
import pw.pref.exception.PreferenceException;
import pw.pref.util.PreferenceUtil;

@Service
public class AuthService {

    private RestServiceClient defaultRestServiceClient;
    private PreferenceUtil preferenceUtil;

    @Autowired
    public AuthService(PreferenceUtil preferenceUtil, RestServiceClient defaultRestServiceClient) {
        this.preferenceUtil = preferenceUtil;
        this.defaultRestServiceClient = defaultRestServiceClient;
    }

    public void authenticate(ApiContext apiContext) throws PreferenceException {
        String authToken = apiContext.getAuthToken();
        if (preferenceUtil.isNullOrEmpty(authToken)) {
            if (!isAppKeyValid(apiContext) || !isUserIdValid(apiContext)) {
                throw new PreferenceException("Authentication failed");
            }
        } else {
            String userId = validateAuthTokenAndGetUserId(authToken);
            apiContext.setUserId(userId);
        }
    }

    private boolean isAppKeyValid(ApiContext apiContext) {
        return ApiConstants.PREF_APP_KEY.equals(apiContext.getAppKey());
    }

    private String validateAuthTokenAndGetUserId(String authToken) throws PreferenceException {
        final AuthenticatedUser user = validateAuthToken(authToken);
        return user.getUuid().toString();
    }

    private AuthenticatedUser validateAuthToken(String authToken) throws PreferenceException {
        final AuthenticatedUser user;
        final ServiceResponse serviceResponse = defaultRestServiceClient.callRestApi(
                ApiConstants.USER_APP_URL, ApiConstants.AUTH_VALIDATE_TOKEN_CONTEXT,
                ApiConstants.AUTHTOKEN_PARAMETER, authToken);
        if (!serviceResponse.hasError()) {
            user = (AuthenticatedUser) serviceResponse.getData();
        } else {
            throw new PreferenceException("Authentication failed");
        }
        return user;
    }

    private boolean isUserIdValid(ApiContext apiContext) {
        final ServiceResponse serviceResponse = defaultRestServiceClient.callRestApi(
                ApiConstants.USER_APP_URL, ApiConstants.USER_BY_ID_CONTEXT,
                ApiConstants.USER_ID_PARAMETER, apiContext.getUserId());
        return !serviceResponse.hasError();
    }
}
