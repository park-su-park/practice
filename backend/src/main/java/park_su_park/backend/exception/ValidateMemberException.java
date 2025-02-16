package park_su_park.backend.exception;

public class ValidateMemberException extends RuntimeException{

    public ValidateMemberException() {
        super();
    }

    public ValidateMemberException(String message) {
        super(message);
    }

    public ValidateMemberException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValidateMemberException(Throwable cause) {
        super(cause);
    }

    protected ValidateMemberException(String message, Throwable cause, boolean enableSuppression,
        boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
