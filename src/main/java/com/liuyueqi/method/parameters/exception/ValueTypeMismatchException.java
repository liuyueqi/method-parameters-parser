package com.liuyueqi.method.parameters.exception;

import lombok.Getter;

@Getter
public class ValueTypeMismatchException extends ValueParseException {

    private static final long serialVersionUID = -8260437236087482764L;
    
    private Class<?> type;
    private Object value;

    public ValueTypeMismatchException(Class<?> type, Object value) {

        super(String.format("Cannot parse value: %s as type: %s", value, type));
        this.type = type;
        this.value = value;
    }
}
