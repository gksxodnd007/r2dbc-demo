package org.codingsquid.r2dbc.exception;

/**
 * @author hubert.squid
 * @since 2020.05.10
 */
public abstract class BaseException extends RuntimeException {

    private int errorCode;
    private String errorMessage;

    public BaseException(int errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
