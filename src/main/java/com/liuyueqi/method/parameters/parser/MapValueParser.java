package com.liuyueqi.method.parameters.parser;

import java.util.Map;

public class MapValueParser implements ValueParser {

    private Class<?> keyGenericType;
    private Class<?> valueGenericType;
    
    public MapValueParser() {
        this(null, null);
    }

    public MapValueParser(Class<?> keyGenericType, Class<?> valueGenericType) {
        
        if (keyGenericType == null) {
            this.keyGenericType = NullType.class;
        } else {
            this.keyGenericType = keyGenericType;
        }
        
        if (valueGenericType == null) {
            this.valueGenericType = NullType.class;
        } else {
            this.valueGenericType = valueGenericType;
        }
    }

    @Override
    public Class<?>[] support() {
        return new Class[] { Map.class };
    }

    @Override
    public Object parse(String value) {
        return null;
    }

}
