package pw.pref.model;

public class Preference {

    private String appId;
    private String key;
    private String value;
    private String userId;

    public Preference(String appId, String key, String value, String userId) {
        this.appId = appId;
        this.key = key;
        this.value = value;
        this.userId = userId;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
