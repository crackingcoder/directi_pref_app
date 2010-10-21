package pw.authentication.web.api.http.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import org.apache.http.HttpResponse;
import org.fest.reflect.core.Reflection;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias("response")
public class ServiceResponse implements Serializable
{
	public static class IgnoredParams
	{
		@SuppressWarnings("unused")
		@XStreamAsAttribute
		private final String reason;

		@SuppressWarnings("unused")
		@XStreamImplicit(itemFieldName = "param")
		private final List<String> paramNames;

		public IgnoredParams(String reason, String... paramNames)
		{
			this.reason = reason;
			this.paramNames = Arrays.asList(paramNames);
		}

	}

	private static final long serialVersionUID = -4561217359416655830L;

	public interface Status
	{
		String SUCCESS = "SUCCESS";
		String ERROR = "ERROR";
	}

	@Expose
	private String status;
	@Expose
	private String errorCode;
	@Expose
	private String errorMessage;
	@XStreamAlias("message")
	@Expose
	private String successMessage;
	@Expose
	private Object data;
	@Expose
	private Class<? extends Exception> exceptionClass;
	@SuppressWarnings("unused")
	@Expose
	private IgnoredParams ignoredParams;
	@Expose
	private String infoCode;

	public String getStatus()
	{
		return status;
	}

	private void setStatus(final String status)
	{
		this.status = status;
	}

	public String getErrorCode()
	{
		return errorCode;
	}

	private void setErrorCode(final String errorCode)
	{
		this.errorCode = errorCode;
	}

	public String getErrorMessage()
	{
		return errorMessage;
	}

	private void setErrorMessage(final String errorMsg)
	{
		errorMessage = errorMsg;
	}

	public Object getData()
	{
		return data;
	}

	@Override
	public String toString()
	{
		String str = "";
		str += "Status " + getStatus();
		str += "errorCode " + getErrorCode();
		return str;
	}

	public String toJson()
	{
		return new Gson().toJson(this);

	}

	public void setData(final Object object)
	{

		data = object;
	}

	public void setErrorResponse(Class<? extends Exception> exceptionClass, final String errorCode,
			final String errorMsg)
	{
		this.exceptionClass = exceptionClass;
		setStatus(ServiceResponse.Status.ERROR);
		setErrorCode(errorCode);
		setErrorMessage(errorMsg);
	}

	public void setSuccessResponse()
	{
		setStatus(ServiceResponse.Status.SUCCESS);
	}

	public Class<? extends Exception> getExceptionClass()
	{
		return exceptionClass;
	}

	public boolean hasError()
	{
		return getStatus().equals(ServiceResponse.Status.ERROR);
	}

	public void propogateException() throws Exception
	{
		Exception exception;
		try
		{
			exception = knownException();
		}
		catch (Exception error)
		{
			exception = unknownException();
		}
		throw exception;
	}

	private Exception unknownException()
	{
		return Reflection.constructor().withParameterTypes(String.class).in(exceptionClass).newInstance(errorMessage);
	}

	private Exception knownException()
	{
		return Reflection.constructor().withParameterTypes(String.class, String.class).in(exceptionClass).newInstance(
			errorCode, errorMessage);
	}

	public void setSuccessMessage(String successMessage)
	{
		this.successMessage = successMessage;
	}

	public void setIgnoredParams(IgnoredParams ignoredParams)
	{
		this.ignoredParams = ignoredParams;
	}

	public String getSuccessMessage()
	{
		return successMessage;
	}

	public void setInfoCode(String infoCode) {
		this.infoCode = infoCode;
	}

	public String getInfoCode() {
		return infoCode;
	}

	public static ServiceResponse fromHttpResponse(final HttpResponse httpResponse) throws IOException
	{
		BufferedReader bufferedReader =
				new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
		final StringBuilder content = new StringBuilder();
		String line;
		while ((line = bufferedReader.readLine()) != null)
		{
			content.append(line);
		}
		XStream stream = new XStream();
		stream.processAnnotations(ServiceResponse.class);
		return (ServiceResponse) stream.fromXML(content.toString());
	}
}
