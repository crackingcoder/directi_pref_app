package pw.pref.api.helper;

import pw.pref.constants.ApiConstants;

public class ApiResult {
    private String status;
    private String message;

    public ApiResult(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public boolean isSuccess() {
        return ApiConstants.SUCCESS.equals(this.status);
    }
}
