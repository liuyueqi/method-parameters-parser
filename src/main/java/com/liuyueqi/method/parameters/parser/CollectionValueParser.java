package com.liuyueqi.method.parameters.parser;

import lombok.AccessLevel;
import lombok.Getter;

public abstract class CollectionValueParser implements ValueParser {

    @Getter(AccessLevel.PROTECTED)
    private Class<?> genericType;

    public CollectionValueParser(Class<?> genericType) {
        
        if (genericType == null) {
            this.genericType = NullType.class;
        } else {
            this.genericType = genericType;
        }
    }
    
}
