package park_su_park.backend.exception;

public class BadRequestParamException extends RuntimeException{

    public BadRequestParamException() {
        super();
    }

    public BadRequestParamException(String message) {
        super(message);
    }

    public BadRequestParamException(String message, Throwable cause) {
        super(message, cause);
    }

    public BadRequestParamException(Throwable cause) {
        super(cause);
    }

    protected BadRequestParamException(String message, Throwable cause, boolean enableSuppression,
        boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
