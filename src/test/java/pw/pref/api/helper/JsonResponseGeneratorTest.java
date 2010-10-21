package pw.pref.api.helper;

import org.mockito.ArgumentCaptor;
import static org.mockito.Mockito.*;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import static pw.pref.constants.ApiConstants.FAILURE;
import static pw.pref.constants.ApiConstants.SUCCESS;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class JsonResponseGeneratorTest {
    private JsonResponseGenerator jsonResponseGenerator = new JsonResponseGenerator();
    private HttpServletResponse response;
    private PrintWriter writer;

    @BeforeMethod
    public void setUp() throws IOException {
        response = mock(HttpServletResponse.class);
        writer = mock(PrintWriter.class);
        when(response.getWriter()).thenReturn(writer);
    }

    @Test
    public void jsonGenerated() throws IOException {
        ApiResult apiResult = new ApiResult(SUCCESS, null, null);
        jsonResponseGenerator.generateResponse(response, apiResult);

        ArgumentCaptor<String> jsonStringCaptor = ArgumentCaptor.forClass(String.class);
        verify(writer).write(jsonStringCaptor.capture());
        String json = jsonStringCaptor.getValue();
        assertEquals(json, "{\"status\":\"" + SUCCESS + "\"}");
    }

    @Test
    public void jsonGeneratedWithMessage() throws IOException {
        ApiResult apiResult = new ApiResult(FAILURE, "Some error occurred", null);
        jsonResponseGenerator.generateResponse(response, apiResult);

        ArgumentCaptor<String> jsonStringCaptor = ArgumentCaptor.forClass(String.class);
        verify(writer).write(jsonStringCaptor.capture());
        String json = jsonStringCaptor.getValue();
        assertEquals(json, "{\"status\":\"" + FAILURE + "\",\"message\":\"Some error occurred\"}");
    }
}
