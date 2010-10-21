package pw.pref.api.helper;

import pw.pref.constants.ApiConstants;
import com.google.gson.annotations.Expose;

public class ApiResult {
    @Expose
    private String status;
    @Expose
    private String message;
    @Expose
    private Object data;

    public ApiResult(String status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public boolean isSuccess() {
        return ApiConstants.SUCCESS.equals(this.status);
    }
}
