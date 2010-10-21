package pw.pref.api.helper;

import pw.pref.constants.ApiConstants;
import pw.pref.model.Preference;

import javax.servlet.http.HttpServletRequest;

public class ApiContext {

    private String httpMethod;
    private String key;
    private String value;
    private String appId;
    private String authToken;
    private String userId;
    private String status;
    private String message;
    private String appKey;
    private Object data;
    private boolean userIdOptional;
    private boolean keyOptional;

    public ApiContext(HttpServletRequest httpServletRequest) {
        this.httpMethod = httpServletRequest.getMethod();
        this.key = httpServletRequest.getParameter("key");
        this.value = httpServletRequest.getParameter("value");
        this.appId = httpServletRequest.getParameter("app_id");
        this.authToken = httpServletRequest.getParameter("auth_token");
        this.appKey = httpServletRequest.getParameter("app_key");
        this.userId = httpServletRequest.getParameter("user_id");
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public String getKey() {
        return key;
    }

    public String getAppId() {
        return appId;
    }

    public String getAuthToken() {
        return authToken;
    }

    public String getUserId() {
        return userId;
    }

    public void setFailureStatus(String message) {
        this.message = message;
        this.status = ApiConstants.FAILURE;
    }

    public void setSuccessStatus() {
        this.status = ApiConstants.SUCCESS;
    }

    public ApiResult getResult() {
        return new ApiResult(status, message, data);
    }

    public Preference getPreference() {
        return new Preference(appId, key, value, userId);
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public void setUserIdOptional(boolean userIdOptional) {
        this.userIdOptional = userIdOptional;
    }

    public boolean isUserIdOptional() {
        return userIdOptional;
    }

    public void setKeyOptional(boolean keyOptional) {
        this.keyOptional = keyOptional;
    }

    public boolean isKeyOptional() {
        return keyOptional;
    }
}
