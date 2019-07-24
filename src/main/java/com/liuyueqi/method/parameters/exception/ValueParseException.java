package com.liuyueqi.method.parameters.exception;

import com.liuyueqi.method.parameters.TypeInfo;

public class ValueParseException extends RuntimeException {

    private static final long serialVersionUID = -7019291492084785789L;

    public ValueParseException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValueParseException(String message) {
        super(message);
    }

    public ValueParseException(Object value, TypeInfo typeInfo) {
        super(String.format("Cannot parse value to type, value: %s, type: %s", value, typeInfo));
    }

}
