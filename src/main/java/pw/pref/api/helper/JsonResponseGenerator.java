package pw.pref.api.helper;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JsonResponseGenerator {

    public void generateResponse(HttpServletResponse httpServletResponse, ApiResult apiResult) throws IOException {
        setHttpResultCode(httpServletResponse, apiResult);
        setJsonContentType(httpServletResponse);
        writeResultAsJson(httpServletResponse, apiResult);
    }

    private void setJsonContentType(HttpServletResponse httpServletResponse) {
        httpServletResponse.setContentType("application/json; charset=utf-8");
    }

    private void writeResultAsJson(HttpServletResponse httpServletResponse, ApiResult apiResult) throws IOException {
        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting()
                .create();
        String json = gson.toJson(apiResult);
        httpServletResponse.getWriter().write(json);
    }

    private void setHttpResultCode(HttpServletResponse httpServletResponse, ApiResult apiResult) {
        if (!apiResult.isSuccess()) {
            httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}