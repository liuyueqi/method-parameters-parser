package com.liuyueqi.method.parameters.parser;

public class StringValueParser extends BaseTypeValueParser {

    @Override
    public Class<?>[] support() {
        return new Class[] { String.class };
    }

    @Override
    public Object parse(String value) {
        return value;
    }
}
