package pw.core.exception;

import java.util.Arrays;
import java.util.Locale;

import org.springframework.context.NoSuchMessageException;
import org.springframework.context.support.MessageSourceAccessor;

public abstract class PwRuntimeException extends RuntimeException implements IPwException
{
	private static final long serialVersionUID = 5806757209827783011L;
	protected String errorCode;
	protected String[] errorArgs = new String[] {};

	@Deprecated
	public PwRuntimeException(final String errorCode, final String[] errorArgs, final String message)
	{
		super(message);
		this.errorCode = errorCode;
		this.errorArgs = errorArgs;
	}

	public PwRuntimeException(final String errorCode)
	{
		super("An unknown error has occurred - [" + errorCode + "]");
		this.errorCode = errorCode;
	}

	public PwRuntimeException(final String errorCode, final String[] errorArgs)
	{
		super("An unknown error has occurred - [" + errorCode + "] ["
				+ (errorArgs != null ? Arrays.asList(errorArgs) : "") + "]");
		this.errorCode = errorCode;
		this.errorArgs = errorArgs;
	}

	public PwRuntimeException(final String errorCode, final Throwable t)
	{
		super("An unknown error has occurred - [" + errorCode + "]", t);
		this.errorCode = errorCode;
	}

	public String getErrorCode()
	{
		return errorCode;
	}

	public String[] getErrorArgs()
	{
		return errorArgs;
	}

	public String getErrorMessage(final MessageSourceAccessor messageSourceAccessor, final Locale locale)
	{
		try
		{
			return messageSourceAccessor.getMessage(getErrorCode(), getErrorArgs(), locale);
		}
		catch (NoSuchMessageException exception)
		{
			return getErrorCode();
		}

	}

}
