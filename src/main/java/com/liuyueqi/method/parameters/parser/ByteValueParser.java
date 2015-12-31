package com.liuyueqi.method.parameters.parser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ByteValueParser extends BaseTypeValueParser {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(ByteValueParser.class);

    @Override
    public Class<?>[] support() {
        return new Class[] { Byte.class, byte.class };
    }

    @Override
    public Object parse(String value) {
        
        if (value == null) {
            return null;
        }
        
        try {
            return Byte.valueOf(value);
        } catch (Exception e) {
            LOGGER.error(String.format("Fail to parse [%s] to byte", value));
            return null;
        }
    }

}
