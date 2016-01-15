package com.liuyueqi.method.parameters.parser;

import java.util.Map;

import com.liuyueqi.method.parameters.TypeInfo;
import com.liuyueqi.method.parameters.util.JsonValueUtils;
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

public class ObjectValueParser implements ValueParser {

    private static final TypeInfo[] SUPPORTED_TYPES = new TypeInfo[0];
    private static final TypeInfo LIST_TYPE_INFO = new TypeInfo(List.class);
    private static final TypeInfo MAP_TYPE_INFO = new TypeInfo(Map.class);
    
    @Override
    public TypeInfo[] support() {
        return SUPPORTED_TYPES;
    }

    @Override
    public Object parse(Object value) {

        if (value == null) {
            return null;
        }
        
        if (value instanceof String) {
            return parseString((String) value);
        }
        
        return value;
    }

    private Object parseString(String value) {
        
        if (JsonValueUtils.isMap(value)) {
            ValueParser parser = CommonValueParserFactory.getInstance().getValueParser(LIST_TYPE_INFO);
            return parser.parse(value);
        }
        
        if (JsonValueUtils.isArray(value)) {
            ValueParser parser = CommonValueParserFactory.getInstance().getValueParser(MAP_TYPE_INFO);
            return parser.parse(value);
        }
        
        return value;
    }
}
