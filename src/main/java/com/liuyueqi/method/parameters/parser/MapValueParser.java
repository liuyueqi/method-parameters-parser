package com.liuyueqi.method.parameters.parser;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.liuyueqi.method.parameters.util.JsonTypeUtil;
import com.liuyueqi.method.parameters.util.JsonValueUtil;
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

public class MapValueParser implements ValueParser {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(MapValueParser.class);

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
        
        if (value == null) {
            return null;
        }
        
        if (!JsonValueUtil.isMap(value)) {
            LOGGER.error(String.format("%s is not a map", value));
            return null;
        }
        
        Map<String, Object> jsonObject = JSON.parseObject(value);
        if (this.valueGenericType != NullType.class) {
            
            ValueParserFactory factory = DefaultValueParserFactory.getInstance();
            if (JsonTypeUtil.isBaseType(this.valueGenericType)) {

                BaseTypeValueParser parser = factory.getBaseTypeValueParser(this.valueGenericType);
                Map<String, Object> copy = new HashMap<String, Object>();
                for (Map.Entry<String, Object> entry : jsonObject.entrySet()) {
                    copy.put(entry.getKey(), parser.parse(entry.getValue() == null ? null : String.valueOf(entry.getValue())));
                }
                jsonObject = copy;
            
            } else if (List.class == this.valueGenericType) {
                
                ListValueParser parser = factory.getListValueParser(this.valueGenericType);
                Map<String, Object> copy = new HashMap<String, Object>();
                for (Map.Entry<String, Object> entry : jsonObject.entrySet()) {
                    copy.put(entry.getKey(), parser.parse(entry.getValue() == null ? null : String.valueOf(entry.getValue())));
                }
                
                
            } else if (Set.class == this.valueGenericType) {
                
            } else if (Map.class == this.valueGenericType) {
                
                factory.getMapValueParser(null, null);
                
            } else {
                
            }
        }
        
        
        
        return null;
    }

    public static void main(String[] args) {
        
        JSONObject jsonObject = JSON.parseObject("{1: 100, 2: 200}");
        Iterator<String> iterator = jsonObject.keySet().iterator();
        while (iterator.hasNext()) {
            System.out.println(jsonObject.get(iterator.next()).getClass());
        }
    }
}
