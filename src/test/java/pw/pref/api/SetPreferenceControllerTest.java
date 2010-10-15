package pw.pref.api;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.springframework.validation.BindException;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import org.mockito.internal.matchers.Matches;
import org.mockito.Matchers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pw.pref.service.PreferenceService;
import pw.pref.exception.PreferenceException;

public class SetPreferenceControllerTest {

    private SetPreferenceController setPreferenceController;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private Object command;
    private BindException bindException;
    private PreferenceService preferenceService;

    @BeforeTest
    public void setUp() {
        preferenceService = mock(PreferenceService.class);
        this.setPreferenceController = new SetPreferenceController(preferenceService);
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        command = new Object();
        bindException = mock(BindException.class);
    }

    @Test
    public void setPreference() throws Exception, PreferenceException {
        setPreferenceController.onSubmit(request, response, command, bindException);
        verify(preferenceService).setPreference(anyString(), anyString(), anyString(), anyString(), anyString());
    }
}
