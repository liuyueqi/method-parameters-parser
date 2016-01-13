package com.liuyueqi.method.parameters.parser;

import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.liuyueqi.method.parameters.TypeInfo;
import com.liuyueqi.method.parameters.exception.ValueParseException;
import com.liuyueqi.method.parameters.util.JsonValueUtils;

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

        if (value instanceof String) {
            return parseString((String) value);
        }
        
        if (value instanceof Collection) {
            return parseCollection((Collection<?>) value);
        }
        
        throw new ValueParseException("");
    }

    private List<?> parseString(String value) {

        value = value.trim();

        if (!JsonValueUtils.isArray(value)) {
            LOGGER.error(String.format("%s is not an array", value));
            return null;
        }

        return parseCollection(JSON.parseArray(value));
    }
}
