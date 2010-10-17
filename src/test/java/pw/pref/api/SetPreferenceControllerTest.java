package pw.pref.api;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertEquals;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Matchers.any;
import org.mockito.Matchers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pw.pref.service.PreferenceService;
import pw.pref.service.AuthService;
import pw.pref.exception.PreferenceException;

import java.util.Map;

public class SetPreferenceControllerTest {

    private SetPreferenceController setPreferenceController;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private Object command;
    private BindException bindException;
    private PreferenceService preferenceService;
    private AuthService authService;

    @BeforeTest
    public void setUp() throws PreferenceException {
        preferenceService = mock(PreferenceService.class);
        authService = mock(AuthService.class);
        this.setPreferenceController = new SetPreferenceController(preferenceService, authService);
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        command = new Object();
        bindException = mock(BindException.class);
        when(authService.authenticateToken((String) Matchers.isNotNull())).thenReturn("user123");
        when(authService.authenticateToken(null)).thenThrow(new PreferenceException());
    }

    @Test
    public void setPreference() throws Exception, PreferenceException {
        when(request.getParameter("key")).thenReturn("send_email");
        when(request.getParameter("value")).thenReturn("true");
        when(request.getParameter("app_id")).thenReturn("some_app");
        when(request.getParameter("auth_token")).thenReturn("authtoken123");
        ModelAndView view = setPreferenceController.onSubmit(request, response, command, bindException);
        verify(preferenceService).setPreference("send_email", "true", "some_app", "user123");
        assertNotNull(view);
        Map model = view.getModel();
        assertNotNull(model);
        assertEquals(model.get("status"),"success");
    }

    @Test
    public void setPreferenceThrowsError() throws Exception, PreferenceException {
        when(request.getParameter("key")).thenReturn("send_email");
        when(request.getParameter("value")).thenReturn("true");
        when(request.getParameter("app_id")).thenReturn("some_app");
        when(request.getParameter("auth_token")).thenReturn(null);
        ModelAndView view = setPreferenceController.onSubmit(request, response, command, bindException);
        assertNotNull(view);
        Map model = view.getModel();
        assertNotNull(model);
        assertEquals(model.get("status"),"failure");
    }
}
