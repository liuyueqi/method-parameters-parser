package com.liuyueqi.method.parameters.parser;

public interface ValueParserFactory {

    BaseTypeValueParser getBaseTypeValueParser(Class<?> type);
    
    ListValueParser getListValueParser(Class<?> genericType);
    
    SetValueParser getSetValueParser(Class<?> genericType);
    
    MapValueParser getMapValueParser(Class<?> keyGenericType, Class<?> valueGenericType);
    
    PojoValueParser getPojoValueParser(Class<?> type);
}
