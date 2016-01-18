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
import com.liuyueqi.method.parameters.exception.PojoInstantiationException;
import com.liuyueqi.method.parameters.exception.ValueParseException;
import com.liuyueqi.method.parameters.exception.ValueTypeMismatchException;
import com.liuyueqi.method.parameters.util.JsonValueUtils;

public class PojoValueParser implements ValueParser {

    private static final Logger LOGGER = LoggerFactory.getLogger(PojoValueParser.class);
    
    private static final TypeInfo[] SUPPORTED_TYPES = new TypeInfo[0];

    private TypeInfo type;

    public PojoValueParser(TypeInfo type) {

        if (type == null) {
            throw new ValueParseException("TypeInfo cannot be null");
        }
        this.type = type;
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

        Class<?> rawType = this.type.getRawType();

        Object instance = null;
        try {
            instance = rawType.newInstance();
        } catch (InstantiationException e) {
            throw new PojoInstantiationException(rawType);
        } catch (IllegalAccessException e) {
            throw new PojoInstantiationException(rawType);
        }

        for (Map.Entry<String, ?> entry : value.entrySet()) {

            TypeInfo typeInfo = null;
            Object parsedVal = null;
            try {

                Field field = rawType.getDeclaredField(entry.getKey());
                typeInfo = new TypeInfo(field.getGenericType());

                ValueParser parser = CommonValueParserFactory.getInstance().getValueParser(typeInfo);

                PropertyDescriptor propertyDescriptor = new PropertyDescriptor(entry.getKey(), rawType);
                parsedVal = parser.parse(entry.getValue());
                propertyDescriptor.getWriteMethod().invoke(instance, parsedVal);

            } catch (NoSuchFieldException e) {
                LOGGER.warn("Cannot find field: [{}] in type: [{}]", entry.getKey(), rawType);
                continue;
            } catch (SecurityException e) {
                LOGGER.warn("Cannot access field: [{}] in type: [{}]", entry.getKey(), rawType);
                continue;
            } catch (IntrospectionException e) {
                LOGGER.warn("Cannot find getter/setter of field: [{}] in type: [{}]", entry.getKey(), rawType);
                continue;
            } catch (IllegalAccessException e) {
                LOGGER.warn("Cannot access getter/setter of field: [{}] in type: [{}]", entry.getKey(), rawType);
                continue;
            } catch (InvocationTargetException e) {
                LOGGER.warn("Fail to invoke getter/setter of field: [{}] in type: [{}]", entry.getKey(), rawType);
                continue;
            } catch (IllegalArgumentException e) {
                throw new ValueTypeMismatchException(typeInfo.getRawType(), parsedVal);
            }
        }

        return instance;
    }
}
