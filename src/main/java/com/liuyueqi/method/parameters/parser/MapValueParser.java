package com.liuyueqi.method.parameters.parser;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.liuyueqi.method.parameters.TypeInfo;
import com.liuyueqi.method.parameters.exception.ValueParseException;
import com.liuyueqi.method.parameters.util.JsonValueUtils;

public class MapValueParser implements ValueParser {

    private static final Logger LOGGER = LoggerFactory.getLogger(MapValueParser.class);
    private static final TypeInfo[] SUPPORTED_TYPES = new TypeInfo[0];

    private TypeInfo keyGenericType;
    private TypeInfo valueGenericType;
    
    public MapValueParser() {
        this(null, null);
    }

    public MapValueParser(TypeInfo keyGenericType, TypeInfo valueGenericType) {
        this.keyGenericType = keyGenericType;
        this.valueGenericType = valueGenericType;
    }

    @Override
    public TypeInfo[] support() {
        return SUPPORTED_TYPES;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Object parse(Object value) {
        
        if (value == null) {
            return null;
        }
        
        if (value instanceof String) {
            return parserString((String) value);
        }
        
        if (value instanceof Map) {
            return parseMap((Map<String, ?>) value);
        }

        throw new ValueParseException("");
    }
    
    private Map<?, ?> parserString(String value) {
        
        if (!JsonValueUtils.isMap(value)) {
            LOGGER.error(String.format("%s is not a map", value));
            return null;
        }
        
        return parseMap(JSON.parseObject(value));
    }
    
    private Map<?, ?> parseMap(Map<String, ?> value) {
        
        if (value.isEmpty()) {
            return value;
        }
        
        if (this.keyGenericType == null && this.valueGenericType == null) {
            return value;
        }
        
        CommonValueParserFactory factory = CommonValueParserFactory.getInstance();
        
        Map<Object, Object> result = new HashMap<Object, Object>();
        for (Map.Entry<String, ?> entry : value.entrySet()) {
            
            Object key = null;
            if (this.keyGenericType == null) {
                key = entry.getKey();
            } else {
                ValueParser parser = factory.getValueParser(this.keyGenericType);
                key = parser.parse(entry.getKey());
            }
            
            Object val = null;
            if (this.valueGenericType == null) {
                val = entry.getValue();
            } else {
                ValueParser parser = factory.getValueParser(this.valueGenericType);
                val = parser.parse(entry.getValue());
            }
            
            result.put(key, val);
        }
        return result;
    }
}
