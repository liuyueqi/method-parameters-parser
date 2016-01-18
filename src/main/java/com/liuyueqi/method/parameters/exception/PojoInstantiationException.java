package com.liuyueqi.method.parameters.exception;

import lombok.Getter;

public class PojoInstantiationException extends ValueParseException {

    private static final long serialVersionUID = 5728098238340978036L;
    
    @Getter
    private Class<?> type;

    public PojoInstantiationException(Class<?> type) {

        super(String.format("Cannot instantiate pojo of type: [%s]", type));
        this.type = type;
    }
}
