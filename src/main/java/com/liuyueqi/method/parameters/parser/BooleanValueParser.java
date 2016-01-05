package com.liuyueqi.method.parameters.parser;

import org.apache.commons.lang3.ArrayUtils;

import com.liuyueqi.method.parameters.TypeInfo;

public class BooleanValueParser extends BaseValueParser {
    
    private static final TypeInfo[] SUPPORTED_TYPES = { TypeInfo.BOOLEAN, TypeInfo.PRIMITIVE_BOOLEAN };

    @Override
    public TypeInfo[] support() {
        return SUPPORTED_TYPES;
    }

    @Override
    public Object parse(Object value) {
        
        if (value == null) {
            return null;
        }
        
        if (ArrayUtils.contains(SUPPORTED_TYPES, value.getClass())) {
            return value;
        }
        
        return Boolean.valueOf(value.toString());
    }

}
