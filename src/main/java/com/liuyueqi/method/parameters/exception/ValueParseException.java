package com.liuyueqi.method.parameters.exception;

public class ValueParseException extends RuntimeException {

    private static final long serialVersionUID = -7019291492084785789L;

    public ValueParseException() {
        super();
    }

    public ValueParseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public ValueParseException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValueParseException(String message) {
        super(message);
    }

    public ValueParseException(Throwable cause) {
        super(cause);
    }

}
