package com.liuyueqi.method.parameters.parser;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DefaultValueParserFactory implements ValueParserFactory {
    
    private static final Map<Class<?>, BaseTypeValueParser> BASE_TYPE_VALUE_PARSER_MAP = 
            new HashMap<Class<?>, BaseTypeValueParser>();
    
    private static final ConcurrentHashMap<Class<?>, ListValueParser> LIST_VALUE_PARSER_MAP = 
            new ConcurrentHashMap<Class<?>, ListValueParser>();
    
    private static final ConcurrentHashMap<Class<?>, SetValueParser> SET_VALUE_PARSER_MAP = 
            new ConcurrentHashMap<Class<?>, SetValueParser>();
    
    private static final ConcurrentHashMap<String, MapValueParser> MAP_VALUE_PARSER_MAP = 
            new ConcurrentHashMap<String, MapValueParser>();
    
    private static final ConcurrentHashMap<Class<?>, PojoValueParser> POJO_VALUE_PARSER_MAP = 
            new ConcurrentHashMap<Class<?>, PojoValueParser>();
    
    static {
        BASE_TYPE_VALUE_PARSER_MAP.put(Boolean.class, new BooleanValueParser());
        BASE_TYPE_VALUE_PARSER_MAP.put(Byte.class, new ByteValueParser());
        BASE_TYPE_VALUE_PARSER_MAP.put(Double.class, new DoubleValueParser());
        BASE_TYPE_VALUE_PARSER_MAP.put(Integer.class, new IntegerValueParser());
        BASE_TYPE_VALUE_PARSER_MAP.put(Long.class, new LongValueParser());
        BASE_TYPE_VALUE_PARSER_MAP.put(Short.class, new ShortValueParser());
        BASE_TYPE_VALUE_PARSER_MAP.put(String.class, new StringValueParser());
    }
    
    private DefaultValueParserFactory() {
    }
    
    public static DefaultValueParserFactory getInstance() {
        return Holder.INSTANCE;
    }
    
    @Override
    public BaseTypeValueParser getBaseTypeValueParser(Class<?> type) {
        
        BaseTypeValueParser parser = BASE_TYPE_VALUE_PARSER_MAP.get(type);
        if (parser == null) {
            throw new IllegalArgumentException(String.format(
                    "Cannot find BaseTypeValueParser for type: %s", type.getSimpleName()));
        }
        return parser;
    }

    @Override
    public ListValueParser getListValueParser(Class<?> genericType) {
        
        if (genericType == null) {
            genericType = NullType.class;
        }

        if (!LIST_VALUE_PARSER_MAP.containsKey(genericType)) {
            LIST_VALUE_PARSER_MAP.putIfAbsent(genericType, new ListValueParser(genericType));
        }
        return LIST_VALUE_PARSER_MAP.get(genericType);
    }

    @Override
    public SetValueParser getSetValueParser(Class<?> genericType) {
        
        if (genericType == null) {
            genericType = NullType.class;
        }

        if (!SET_VALUE_PARSER_MAP.containsKey(genericType)) {
            SET_VALUE_PARSER_MAP.putIfAbsent(genericType, new SetValueParser(genericType));
        }
        return SET_VALUE_PARSER_MAP.get(genericType);
    }

    @Override
    public MapValueParser getMapValueParser(Class<?> keyGenericType, Class<?> valueGenericType) {
        
        String keyType = "";
        if (keyGenericType != null) {
            keyType = keyGenericType.getSimpleName();
        }
        
        String valueType = "";
        if (valueGenericType != null) {
            valueType = valueGenericType.getSimpleName();
        }
        
        String type = keyType + "->" + valueType;
        if (!MAP_VALUE_PARSER_MAP.containsKey(type)) {
            MAP_VALUE_PARSER_MAP.put(type, new MapValueParser(keyGenericType, valueGenericType));
        }
        return MAP_VALUE_PARSER_MAP.get(type);
    }

    @Override
    public PojoValueParser getPojoValueParser(Class<?> type) {
        
        if (type == null) {
            type = NullType.class;
        }
        
        if (!POJO_VALUE_PARSER_MAP.containsKey(type)) {
            POJO_VALUE_PARSER_MAP.putIfAbsent(type, new PojoValueParser(type));
        }
        return POJO_VALUE_PARSER_MAP.get(type);
    }

    private static class Holder {
        private static final DefaultValueParserFactory INSTANCE = new DefaultValueParserFactory();
    }
}
