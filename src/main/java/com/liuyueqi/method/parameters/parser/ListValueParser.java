package com.liuyueqi.method.parameters.parser;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.liuyueqi.method.parameters.util.JsonValueUtil;

public class ListValueParser extends CollectionValueParser {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(ListValueParser.class);

    public ListValueParser(Class<?> genericType) {
        super(genericType);
    }

    @Override
    public Class<?>[] support() {
        return new Class[] { List.class };
    }

    @Override
    public Object parse(String value) {
        
        if (value == null) {
            return null;
        }
        
        value = value.trim();
        
        if (!JsonValueUtil.isArray(value)) {
            LOGGER.error(String.format("%s is not an array", value));
            return null;
        }

        if (getGenericType() == NullType.class) {
            return JSON.parseArray(value);
        }
        
        return JSON.parseArray(value, getGenericType());
    }

}
