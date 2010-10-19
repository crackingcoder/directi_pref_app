package pw.pref.api.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pw.pref.exception.PreferenceException;
import pw.pref.util.PreferenceUtil;

import java.util.List;

@Component
public class ApiValidator {
    private PreferenceUtil preferenceUtil;

    @Autowired
    public ApiValidator(PreferenceUtil preferenceUtil) {
        this.preferenceUtil = preferenceUtil;
    }

    public void validate(ApiContext apiContext, List<String> supportedMethods) throws PreferenceException {
        verifyHttpMethod(apiContext, supportedMethods);
        validateParameters(apiContext);
    }

    private void validateParameters(ApiContext apiContext) throws PreferenceException {
        if (preferenceUtil.anyOneIsNullOrEmpty(apiContext.getAppId(), apiContext.getKey(), apiContext.getUserId())) {
            throw new PreferenceException("Missing required parameter");
        }
    }

    private void verifyHttpMethod(ApiContext apiContext, List<String> supportedMethods) throws PreferenceException {
        if (!supportedMethods.contains(apiContext.getHttpMethod())) {
            throw new PreferenceException(
                    "HTTP Method - " + apiContext.getHttpMethod() + " - not allowed for this api");
        }
    }
}
