package com.liuyueqi.method.parameters.parser;

import com.liuyueqi.method.parameters.TypeInfo;

public class StringValueParser extends BaseValueParser {

    private static final TypeInfo[] SUPPORTED_TYPES = { TypeInfo.STRING };

    @Override
    public TypeInfo[] support() {
        return SUPPORTED_TYPES;
    }

    @Override
    public Object parse(Object value) {
        return value;
    }
}
