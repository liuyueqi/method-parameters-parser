package com.liuyueqi.method.parameters.parser;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import com.liuyueqi.method.parameters.TypeInfo;

public class SetValueParser extends CollectionValueParser {
    
    public SetValueParser() {
        super();
    }

    public SetValueParser(TypeInfo genericType) {
        super(genericType);
    }

    @Override
    public TypeInfo[] support() {
        return new TypeInfo[0];
    }

    @Override
    public Object parse(Object value) {
        
        if (value == null) {
            return null;
        }
        
        if (value instanceof Collection) {
//            return new HashSet<Object>(Collection)
        }

        ListValueParser parser = new ListValueParser(getGenericType());

        List<?> result = (List<?>) parser.parse(value);
        if (result == null) {
            return null;
        }

        return new HashSet<Object>(result);
    }

}
