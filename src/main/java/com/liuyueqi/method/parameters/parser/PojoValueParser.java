package com.liuyueqi.method.parameters.parser;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.liuyueqi.method.parameters.TypeInfo;
import com.liuyueqi.method.parameters.util.JsonValueUtil;

public class PojoValueParser implements ValueParser {

    private static final Logger LOGGER = LoggerFactory.getLogger(PojoValueParser.class);

    private TypeInfo type;

    public PojoValueParser(TypeInfo type) {

        if (type == null) {
            throw new IllegalArgumentException("TypeInfo cannot be null");
        }
        this.type = type;
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
        
        if (value.getClass() == type.getRawType()) {
            return value;
        }
        
        if (value instanceof String) {
            return parseString((String) value);
        }
        
        if (value instanceof Map) {
            return parseMap((Map<String, ?>) value);
        }
        
        throw new IllegalArgumentException("");
    }
    
    private Object parseString(String value) {
        
        if (!JsonValueUtil.isMap(value)) {
            LOGGER.error(String.format("%s cannot be parsed to type: %s", value, this.type));
        }
        return JSON.parseObject(value, this.type.getRawType());
    }
    
    private Object parseMap(Map<String, ?> value) {
        
        try {
            
            Object instance = type.getRawType().newInstance();
            BeanUtils.populate(instance, value);
            return instance;
        
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }
}
