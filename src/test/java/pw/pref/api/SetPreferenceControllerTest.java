package pw.pref.api;

import org.mockito.ArgumentCaptor;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pw.pref.api.helper.ApiResult;
import pw.pref.api.helper.ApiValidator;
import pw.pref.api.helper.ApiContext;
import pw.pref.api.helper.JsonResponseGenerator;
import pw.pref.exception.PreferenceException;
import pw.pref.service.AuthService;
import pw.pref.service.PreferenceService;
import pw.pref.model.Preference;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class SetPreferenceControllerTest {

    private SetPreferenceController setPreferenceController;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private PreferenceService preferenceService;
    private AuthService authService;
    private JsonResponseGenerator jsonResponseGenerator;
    private ApiValidator apiValidator;

    @BeforeMethod
    public void setUp() throws PreferenceException, IOException {
        setUpMocks();
        this.setPreferenceController = new SetPreferenceController(preferenceService, authService,
                jsonResponseGenerator, apiValidator);
    }

    private void setUpMocks() {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        apiValidator = mock((ApiValidator.class));
        authService = mock(AuthService.class);
        preferenceService = mock(PreferenceService.class);
        jsonResponseGenerator = mock(JsonResponseGenerator.class);
    }

    @Test
    public void setPreferenceAndReturnJsonResponse() throws Exception, PreferenceException {
        setPreferenceController.handleRequest(request, response);
        verify(apiValidator).validate(any(ApiContext.class), any(List.class));
        verify(authService).authenticate(any(ApiContext.class));
        verify(preferenceService).setPreference(any(Preference.class));
        verifyJsonResponseGeneratorInteractionAndCaptureApiResult();
    }

    private void verifyJsonResponseGeneratorInteractionAndCaptureApiResult() throws IOException {
        ArgumentCaptor<ApiResult> apiResultArgumentCaptor = ArgumentCaptor.forClass(ApiResult.class);
        verify(jsonResponseGenerator)
                .generateResponse(any(HttpServletResponse.class), apiResultArgumentCaptor.capture());
        ApiResult apiResult = apiResultArgumentCaptor.getValue();
        assertEquals(apiResult.getStatus(), "success");
    }
}
