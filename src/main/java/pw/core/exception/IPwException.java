package pw.core.exception;

import java.util.Locale;

import org.springframework.context.support.MessageSourceAccessor;

public interface IPwException
{
	public abstract String[] getErrorArgs();

	public abstract String getMessage();

	public abstract String getErrorCode();

	public String getErrorMessage(MessageSourceAccessor messageSourceAccessor, Locale locale);

}