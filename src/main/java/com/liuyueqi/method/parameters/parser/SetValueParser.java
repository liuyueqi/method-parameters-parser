package com.liuyueqi.method.parameters.parser;

import java.util.Set;

public class SetValueParser extends CollectionValueParser {

    public SetValueParser(Class<?> genericType) {
        super(genericType);
    }

    @Override
    public Class<?>[] support() {
        return new Class[] { Set.class };
    }

    @Override
    public Object parse(String value) {
        // TODO
        return null;
    }

}
