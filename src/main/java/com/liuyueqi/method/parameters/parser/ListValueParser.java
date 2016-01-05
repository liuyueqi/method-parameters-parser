package com.liuyueqi.method.parameters.parser;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.liuyueqi.method.parameters.TypeInfo;
import com.liuyueqi.method.parameters.util.JsonValueUtil;
import com.liuyueqi.method.parameters.util.TypeInfoUtil;

public class ListValueParser extends CollectionValueParser {

    private static final Logger LOGGER = LoggerFactory.getLogger(ListValueParser.class);

    public ListValueParser() {
        super();
    }

    public ListValueParser(TypeInfo genericType) {
        super(genericType);
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
        
        if (value instanceof Collection) {
            return new ArrayList<Object>((Collection<?>) value);
        }

        if (value instanceof String) {
            return parse((String) value);
        }
        
        throw new IllegalArgumentException("");
    }

    private Object parse(String value) {

        value = value.trim();

        if (!JsonValueUtil.isArray(value)) {
            LOGGER.error(String.format("%s is not an array", value));
            return null;
        }

        TypeInfo genericType = getGenericType();
        if (genericType == null) {
            return JSON.parseArray(value);
        }

        if (TypeInfoUtil.isBaseType(genericType) || ArrayUtils.isEmpty(genericType.getGenericTypes())) {
            return JSON.parseArray(value, genericType.getRawType());
        }

        // TODO 泛型为自定义类型的情况
        return JSON.parseArray(value);
    }
}
