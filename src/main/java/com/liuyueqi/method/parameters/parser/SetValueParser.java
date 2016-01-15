package com.liuyueqi.method.parameters.parser;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.liuyueqi.method.parameters.TypeInfo;
import com.liuyueqi.method.parameters.exception.ValueParseException;
import com.liuyueqi.method.parameters.util.JsonValueUtils;

public class SetValueParser extends CollectionValueParser {

    private static final Logger LOGGER = LoggerFactory.getLogger(SetValueParser.class);
    
    public SetValueParser() {
        super();
    }

    public SetValueParser(TypeInfo genericType) {
        super(genericType);
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
            return new HashSet<Object>(parseCollection((Collection<?>) value));
        }
        
        throw new ValueParseException("");
    }

    private Set<?> parseString(String value) {

        value = value.trim();

        if (!JsonValueUtils.isArray(value)) {
            LOGGER.error(String.format("%s is not an set", value));
            return null;
        }

        return new HashSet<Object>(parseCollection(JSON.parseArray(value)));
    }
}
