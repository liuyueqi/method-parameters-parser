package com.liuyueqi.method.parameters.parser;

import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.liuyueqi.method.parameters.TypeInfo;

public class ByteValueParser extends BaseValueParser {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(ByteValueParser.class);

    private static final TypeInfo[] SUPPORTED_TYPES = { TypeInfo.BYTE, TypeInfo.PRIMITIVE_BYTE };

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
            return Byte.valueOf(value.toString());
        } catch (Exception e) {
            LOGGER.error(String.format("Fail to parse [%s] to byte", value));
            return null;
        }
    }

}
