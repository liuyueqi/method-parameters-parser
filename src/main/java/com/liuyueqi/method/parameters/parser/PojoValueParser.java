package com.liuyueqi.method.parameters.parser;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.liuyueqi.method.parameters.TypeInfo;
import com.liuyueqi.method.parameters.exception.ValueParseException;
import com.liuyueqi.method.parameters.util.JsonValueUtils;

public class PojoValueParser implements ValueParser {

    private static final Logger LOGGER = LoggerFactory.getLogger(PojoValueParser.class);

    private TypeInfo type;

    public PojoValueParser(TypeInfo type) {

        if (type == null) {
            throw new ValueParseException("TypeInfo cannot be null");
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

        if (value.getClass() == this.type.getRawType()) {
            return value;
        }

        if (value instanceof String) {
            return parseString((String) value);
        }

        if (value instanceof Map) {
            return parseMap((Map<String, ?>) value);
        }

        throw new ValueParseException("");
    }

    private Object parseString(String value) {

        if (!JsonValueUtils.isMap(value)) {
            LOGGER.error(String.format("%s cannot be parsed to type: %s", value, this.type));
        }
        return parseMap(JSON.parseObject(value));
    }

    private Object parseMap(Map<String, ?> value) {

        try {
            
            Class<?> rawType = this.type.getRawType();
            Object instance = rawType.newInstance();
            
            for (Map.Entry<String, ?> entry : value.entrySet()) {
                
                try {
                    
                    Field field = rawType.getDeclaredField(entry.getKey());
                    TypeInfo typeInfo = new TypeInfo(field.getGenericType());
                    
                    ValueParser parser = CommonValueParserFactory.getInstance().getValueParser(typeInfo);
                    
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(entry.getKey(), rawType);
                    propertyDescriptor.getWriteMethod().invoke(instance, parser.parse(entry.getValue()));
                    
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (SecurityException e) {
                    e.printStackTrace();
                }
            }
            
            return instance;
            
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        
        return null;
    }
}
