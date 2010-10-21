package pw.authentication.api.client;

import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;

public class ThreadSafeHttpClientFactory
{
    private int maxTotalConnections;
    private int connectionTimeOut;
    private int socketTimeOut;
	private int httpPortNumber;
	private int httpsPortNumber;

	private ClientConnectionManager clientConnectionManager;
	private SchemeRegistry schemeRegistry;
	private HttpParams httpParams;
	private HttpClient httpClient;

	public void initialize()
	{
		httpParams = new BasicHttpParams();

        HttpConnectionParams.setConnectionTimeout(httpParams, connectionTimeOut);
        HttpConnectionParams.setSoTimeout(httpParams, socketTimeOut);

        ConnManagerParams.setMaxTotalConnections(httpParams, maxTotalConnections);

		HttpProtocolParams.setVersion(httpParams, HttpVersion.HTTP_1_1);
		HttpProtocolParams.setContentCharset(httpParams, HTTP.DEFAULT_CONTENT_CHARSET);
		HttpProtocolParams.setUseExpectContinue(httpParams, true);

		schemeRegistry = new SchemeRegistry();
		schemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), httpPortNumber));
		schemeRegistry.register(new Scheme("https", SSLSocketFactory.getSocketFactory(), httpsPortNumber));

		clientConnectionManager = new ThreadSafeClientConnManager(httpParams, schemeRegistry);

		httpClient = new DefaultHttpClient(clientConnectionManager, httpParams);
	}

	public HttpClient getHttpClient()
	{
		return httpClient;
	}

	public void setMaxTotalConnections(final int maxTotalConnections)
	{
		this.maxTotalConnections = maxTotalConnections;
	}

	public void setConnectionTimeOut(final int connectionTimeOut)
	{
		this.connectionTimeOut = connectionTimeOut;
	}

	public void setSocketTimeOut(final int socketTimeOut)
	{
		this.socketTimeOut = socketTimeOut;
	}

	public void setHttpPortNumber(final int httpPortNumber)
	{
		this.httpPortNumber = httpPortNumber;
	}

	public void setHttpsPortNumber(final int httpsPortNumber)
	{
		this.httpsPortNumber = httpsPortNumber;
	}

}
