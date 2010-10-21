package pw.authentication.api.client;

import com.thoughtworks.xstream.XStream;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import pw.pref.constants.ApiConstants;
import pw.authentication.web.api.http.client.ServiceResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class RestServiceClient {
    private HttpClient httpClient;

    @Autowired
    void setHttpClient(final @Qualifier("httpClient") HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public ServiceResponse callRestApi(
            final String restApiUrl, final String restApiContextPath, final String... params
    ) {
        try {
            final HttpResponse httpResponse =
                    httpClient.execute(createHttpGetWithParams(restApiUrl, restApiContextPath,
                            addUserAppKeyToParams(params)));
            final ServiceResponse serviceResponse = getServiceResponseFromHttpResponse(httpResponse);

            if (serviceResponse.hasError()) {
                serviceResponse.propogateException();
            }
            return serviceResponse;
        }
        catch (final Exception exception) {
            throw new RuntimeException(exception.getMessage(), exception);
        }
    }

    public ServiceResponse makeRestApiCall(
            final String restApiUrl, final String restApiContextPath,
            final String... params
    ) throws Exception {

        final HttpResponse httpResponse =
                httpClient.execute(
                        createHttpGetWithParams(restApiUrl, restApiContextPath, addUserAppKeyToParams(params)));
        final ServiceResponse serviceResponse = getServiceResponseFromHttpResponse(httpResponse);

        if (serviceResponse.hasError()) {
            serviceResponse.propogateException();
        }
        return serviceResponse;

    }

    private ServiceResponse getServiceResponseFromHttpResponse(final HttpResponse httpResponse) throws IOException {
        final BufferedReader bufferedReader =
                new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
        final StringBuilder content = new StringBuilder();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            content.append(line);
        }
        final XStream stream = new XStream();
        stream.processAnnotations(ServiceResponse.class);
        return (ServiceResponse) stream.fromXML(content.toString());
    }

    private HttpGet createHttpGetWithParams(final String appUrl, final String contextPath, final String... params)
            throws URISyntaxException {
        return new HttpGet((appUrl + contextPath + "?" + createQueryString(params)));
    }

    @SuppressWarnings("deprecation")
    private String createQueryString(final String[] params) {
        final StringBuilder queryString = new StringBuilder();

        for (int i = 0; i < params.length; i += 2) {
            if (queryString.length() != 0) {
                queryString.append("&");
            }
            queryString.append(params[i]);
            queryString.append("=");
            queryString.append(URLEncoder.encode(params[i + 1]));
        }
        return queryString.toString();
    }

    private String[] addUserAppKeyToParams(final String... params) {
        final List<String> paramList = new ArrayList<String>(Arrays.asList(params));
        paramList.add(ApiConstants.APPKEY);
        paramList.add(ApiConstants.USERAPP_KEY);
        return paramList.toArray(new String[paramList.size()]);
    }
}
