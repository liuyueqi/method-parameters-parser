package com.liuyueqi.method.parameters.parser;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.liuyueqi.method.parameters.TypeInfo;
import com.liuyueqi.method.parameters.util.JsonValueUtils;
import com.liuyueqi.method.parameters.util.TypeInfoUtils;
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

public class MapValueParser implements ValueParser {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(MapValueParser.class);

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
        return new TypeInfo[0];
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

        throw new IllegalArgumentException("");
    }
    
    private Object parserString(String value) {
        
        if (!JsonValueUtils.isMap(value)) {
            LOGGER.error(String.format("%s is not a map", value));
            return null;
        }
        
        return parseMap(JSON.parseObject(value));
    }
    
    private Object parseMap(Map<String, ?> value) {
        
        if (value.isEmpty()) {
            return value;
        }
        
        if (this.keyGenericType == null && this.valueGenericType == null) {
            return value;
        }
        
        Map<Object, Object> result = new HashMap<Object, Object>();
        return result;
    }

    public static void main(String[] args) {
        
        JSONObject jsonObject = JSON.parseObject("{{'a': 1}: 100, {'a': 2}: 200}");
        Iterator<String> iterator = jsonObject.keySet().iterator();
        while (iterator.hasNext()) {
            Object key = iterator.next();
            System.out.println(key.getClass());
            System.out.println(jsonObject.get(key).getClass());
        }
    }
}
