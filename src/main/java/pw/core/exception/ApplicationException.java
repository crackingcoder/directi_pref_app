package pw.core.exception;

public class ApplicationException extends PwRuntimeException
{
	private static final long serialVersionUID = 4152037564161877983L;

    @Deprecated
	public ApplicationException(final String errorCode, final String message)
	{
		super(errorCode, null, message);
	}

    public ApplicationException(String errorCode) {
        super(errorCode);
    }

    public ApplicationException(String errorCode, Throwable t) {
        super(errorCode, t);
    }

    public ApplicationException(String errorCode, final String[] errorArgs) {
        super(errorCode,errorArgs);
    }

}