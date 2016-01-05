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
import com.liuyueqi.method.parameters.parser.factory.BaseValueParserFactory;
import com.liuyueqi.method.parameters.util.JsonValueUtil;
import com.liuyueqi.method.parameters.util.TypeInfoUtil;
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
    public Object parse(Object value) {
        
        if (value == null) {
            return null;
        }
        
        if (value instanceof Map) {
            return value;
        }
        
        if (!JsonValueUtil.isMap(value)) {
            LOGGER.error(String.format("%s is not a map", value));
            return null;
        }
        
        Map<String, Object> jsonObject = JSON.parseObject(value);
        if (this.valueGenericType != null) {
            
            if (TypeInfoUtil.isBaseType(this.valueGenericType)) {

                ValueParser parser = BaseValueParserFactory.getInstance()
                        .getValueParser(this.valueGenericType);

                Map<String, Object> copy = new HashMap<String, Object>();
                for (Map.Entry<String, Object> entry : jsonObject.entrySet()) {
                    copy.put(entry.getKey(), parser.parse(entry.getValue() == null ? null : String.valueOf(entry.getValue())));
                }
                jsonObject = copy;
            
            } else if (List.class == this.valueGenericType.getRawType()) {
                
                
                
            } else if (Set.class == this.valueGenericType.getRawType()) {
                
                
                
            } else if (Map.class == this.valueGenericType.getRawType()) {
                
                
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
