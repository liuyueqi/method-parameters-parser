package com.liuyueqi.method.parameters.parser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import lombok.AccessLevel;
import lombok.Getter;

import com.liuyueqi.method.parameters.TypeInfo;

public abstract class CollectionValueParser implements ValueParser {

    private static final TypeInfo[] SUPPORTED_TYPES = new TypeInfo[0];

    @Getter(AccessLevel.PROTECTED)
    private TypeInfo genericType;
    
    public CollectionValueParser() {
        this(null);
    }

    public CollectionValueParser(TypeInfo genericType) {
        this.genericType = genericType;
    }

    @Override
    public TypeInfo[] support() {
        return SUPPORTED_TYPES;
    }
    
    protected List<?> parseCollection(Collection<?> collection) {

        TypeInfo genericType = getGenericType();
        if (genericType == null || collection.isEmpty()) {
            return (collection instanceof List) ? (List<?>) collection : new ArrayList<Object>(collection);
        }
        
        ValueParser parser = CommonValueParserFactory.getInstance().getValueParser(genericType);
        
        List<Object> result = new ArrayList<Object>(collection.size());
        for (Object item : collection) {
            result.add(parser.parse(item));
        }
        return result;
    }
}
