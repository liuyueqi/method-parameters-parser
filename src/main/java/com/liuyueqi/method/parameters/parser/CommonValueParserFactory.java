package com.liuyueqi.method.parameters.parser;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.liuyueqi.method.parameters.TypeInfo;
import com.liuyueqi.method.parameters.exception.ValueParseException;
import com.liuyueqi.method.parameters.util.TypeInfoUtils;

public class CommonValueParserFactory implements ValueParserFactory {
    
    private static final Map<TypeInfo, BaseValueParser> BASE_VALUE_PARSER_MAP = 
            new HashMap<TypeInfo, BaseValueParser>();

    private static final ConcurrentHashMap<TypeInfo, ListValueParser> LIST_VALUE_PARSER_MAP = 
            new ConcurrentHashMap<TypeInfo, ListValueParser>();

    private static final ConcurrentHashMap<TypeInfo, SetValueParser> SET_VALUE_PARSER_MAP = 
            new ConcurrentHashMap<TypeInfo, SetValueParser>();

    private static final ConcurrentHashMap<TypeInfo, MapValueParser> MAP_VALUE_PARSER_MAP = 
            new ConcurrentHashMap<TypeInfo, MapValueParser>();

    private static final ConcurrentHashMap<TypeInfo, PojoValueParser> POJO_VALUE_PARSER_MAP = 
            new ConcurrentHashMap<TypeInfo, PojoValueParser>();
    
    public static CommonValueParserFactory getInstance() {
        return Holder.INSTANCE;
    }
    
    private CommonValueParserFactory() {

        BASE_VALUE_PARSER_MAP.put(TypeInfo.BOOLEAN, new BooleanValueParser());
        BASE_VALUE_PARSER_MAP.put(TypeInfo.BYTE, new ByteValueParser());
        BASE_VALUE_PARSER_MAP.put(TypeInfo.DOUBLE, new DoubleValueParser());
        BASE_VALUE_PARSER_MAP.put(TypeInfo.INTEGER, new IntegerValueParser());
        BASE_VALUE_PARSER_MAP.put(TypeInfo.LONG, new LongValueParser());
        BASE_VALUE_PARSER_MAP.put(TypeInfo.SHORT, new ShortValueParser());
        BASE_VALUE_PARSER_MAP.put(TypeInfo.STRING, new StringValueParser());
    }

    @Override
    public ValueParser getValueParser(TypeInfo type) {
        
        if (type == null) {
            throw new ValueParseException("TypeInfo cannot be null");
        }
        
        if (TypeInfoUtils.isBaseType(type)) {
            BaseValueParser parser = BASE_VALUE_PARSER_MAP.get(type);
            if (parser == null) {
                throw new ValueParseException(String.format("Cannot find BaseValueParser for type: %s", type));
            }
            return parser;
        }
        
        if (TypeInfoUtils.isList(type)) {
            return lookupListValueParser(type);            
        }
        
        if (TypeInfoUtils.isSet(type)) {
            return lookupSetValueParser(type);
        }
        
        if (TypeInfoUtils.isMap(type)) {
            return lookupMapValueParser(type);
        }
        
        return lookupPojoValueParser(type);
    }
    
    private ValueParser lookupListValueParser(TypeInfo type) {
        
        if (LIST_VALUE_PARSER_MAP.contains(type)) {
            return LIST_VALUE_PARSER_MAP.get(type);
        }

        TypeInfo[] genericTypes = type.getGenericTypes();
        TypeInfo genericType = genericTypes.length == 0 ? null : genericTypes[0];
        ListValueParser parser = new ListValueParser(genericType);        
        ListValueParser previousParser = LIST_VALUE_PARSER_MAP.putIfAbsent(type, parser);
        
        if (previousParser == null) {
            return parser;
        }
        return previousParser;
    }
    
    private ValueParser lookupSetValueParser(TypeInfo type) {
        
        if (SET_VALUE_PARSER_MAP.contains(type)) {
            return SET_VALUE_PARSER_MAP.get(type);
        }

        TypeInfo[] genericTypes = type.getGenericTypes();
        TypeInfo genericType = genericTypes.length == 0 ? null : genericTypes[0];
        SetValueParser parser = new SetValueParser(genericType);        
        SetValueParser previousParser = SET_VALUE_PARSER_MAP.putIfAbsent(type, parser);
        
        if (previousParser == null) {
            return parser;
        }
        return previousParser;
    }
    
    private ValueParser lookupMapValueParser(TypeInfo type) {
        
        if (MAP_VALUE_PARSER_MAP.contains(type)) {
            return MAP_VALUE_PARSER_MAP.get(type);
        }

        TypeInfo[] genericTypes = type.getGenericTypes();
        TypeInfo keyGenericType = null;
        if (genericTypes.length > 0) {
            keyGenericType = genericTypes[0];
        }
        TypeInfo valueGenericType = null;
        if (genericTypes.length > 1) {
            valueGenericType = genericTypes[1];
        }
        
        MapValueParser parser = new MapValueParser(keyGenericType, valueGenericType);     
        MapValueParser previousParser = MAP_VALUE_PARSER_MAP.putIfAbsent(type, parser);
        
        if (previousParser == null) {
            return parser;
        }
        return previousParser;
    }
    
    private ValueParser lookupPojoValueParser(TypeInfo type) {
        
        if (POJO_VALUE_PARSER_MAP.contains(type)) {
            return POJO_VALUE_PARSER_MAP.get(type);
        }
        
        PojoValueParser parser = new PojoValueParser(type);
        PojoValueParser previousParser = POJO_VALUE_PARSER_MAP.putIfAbsent(type, parser);
        
        if (previousParser == null) {
            return parser;
        }
        return previousParser;
    }
    
    private static class Holder {
        private static final CommonValueParserFactory INSTANCE = new CommonValueParserFactory();
    }
}
