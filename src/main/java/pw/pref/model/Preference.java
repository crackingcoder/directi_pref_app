package pw.pref.model;

import com.google.gson.annotations.Expose;

import java.util.Date;

public class Preference {

    private long id;
    private String appId;
    @Expose
    private String key;
    @Expose
    private String value;
    private String userId;
    private Date createdDate;
    private Date lastUpdatedDate;

    public Preference() {
    }

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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(Date lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }
}
