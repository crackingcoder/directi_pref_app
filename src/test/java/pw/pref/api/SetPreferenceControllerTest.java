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
import pw.pref.service.ChatService;
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
    private ChatService chatService;
    private static final String NOTIFY_CHAT_SERVER = "notify_chat_server";

    @BeforeMethod
    public void setUp() throws PreferenceException, IOException {
        setUpMocks();
        this.setPreferenceController = new SetPreferenceController();
        this.setPreferenceController.setPreferenceService(preferenceService);
        this.setPreferenceController.setAuthService(authService);
        this.setPreferenceController.setJsonResponseGenerator(jsonResponseGenerator);
        this.setPreferenceController.setApiValidator(apiValidator);
        this.setPreferenceController.setChatService(chatService);
    }

    private void setUpMocks() {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        apiValidator = mock((ApiValidator.class));
        authService = mock(AuthService.class);
        preferenceService = mock(PreferenceService.class);
        jsonResponseGenerator = mock(JsonResponseGenerator.class);
        chatService = mock(ChatService.class);
    }

    @Test
    public void setPreferenceAndReturnJsonResponse() throws Exception {
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

    @Test
    public void notifyChatServer() throws Exception {
        when(request.getParameter(NOTIFY_CHAT_SERVER)).thenReturn(Boolean.TRUE.toString());
        setPreferenceController.handleRequest(request, response);
        verify(request).getParameter(NOTIFY_CHAT_SERVER);
        verify(chatService).notifyChatOfNewValue(any(ApiContext.class));
    }
}
