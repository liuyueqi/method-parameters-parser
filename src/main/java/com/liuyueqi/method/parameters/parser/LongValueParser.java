package com.liuyueqi.method.parameters.parser;

import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.liuyueqi.method.parameters.TypeInfo;

public class LongValueParser extends BaseValueParser {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(LongValueParser.class);

    private static final TypeInfo[] SUPPORTED_TYPES = { TypeInfo.LONG, TypeInfo.PRIMITIVE_LONG };

    @Override
    public TypeInfo[] support() {
        return SUPPORTED_TYPES;
    }

    @Override
    public Object parse(Object value) {
        
        if (value == null) {
            return null;
        }
        
        if (ArrayUtils.contains(SUPPORTED_TYPES, value.getClass())) {
            return value;
        }
        
        try {
            return Long.valueOf(value.toString());
        } catch (Exception e) {
            LOGGER.error(String.format("Fail to parse [%s] to long integer", value));
            return null;
        }
    }

}
