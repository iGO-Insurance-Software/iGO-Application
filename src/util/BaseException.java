package util;

public class BaseException extends Exception {
    private static final long serialVersionUID = 1L;

    private final boolean isSuccess;
    private final int code;
    private final String errorMessage;

    public BaseException(ErrorCode errorCode) {
        this.isSuccess = errorCode.isSuccess();
        this.code = errorCode.getCode();
        this.errorMessage = errorCode.getErrorMessage();
    }

    @Override
    public String getMessage() {
        return this.errorMessage;
    }
}
