package com.liuyueqi.method.parameters.parser;

public class BooleanValueParser extends BaseTypeValueParser {

    @Override
    public Class<?>[] support() {
        return new Class[] { Boolean.class, boolean.class };
    }

    @Override
    public Object parse(String value) {
        return Boolean.valueOf(value);
    }

}
