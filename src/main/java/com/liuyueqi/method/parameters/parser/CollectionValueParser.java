package com.liuyueqi.method.parameters.parser;

import java.util.Collection;

import lombok.AccessLevel;
import lombok.Getter;

import com.liuyueqi.method.parameters.TypeInfo;

public abstract class CollectionValueParser implements ValueParser {

    @Getter(AccessLevel.PROTECTED)
    private TypeInfo genericType;
    
    public CollectionValueParser() {
        this(null);
    }

    public CollectionValueParser(TypeInfo genericType) {
        this.genericType = genericType;
    }
    
    protected Object parse(Collection<?> value) {
        
        return null;
    }
}
