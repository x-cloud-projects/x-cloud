package com.xfeel.cloud.common.exception;

/**
 * @author Admin
 */
public abstract class BaseException extends RuntimeException {

    protected String message;

    public BaseException(Throwable throwable) {
        super(throwable);
    }

    public BaseException(String message) {
        this.message = message;
    }

    public BaseException(String message, Throwable throwable) {
        super(throwable);
        this.message = message;
    }
}
