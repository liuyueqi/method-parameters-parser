package com.liuyueqi.method.parameters.parser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShortValueParser extends BaseTypeValueParser {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(ShortValueParser.class);

    @Override
    public Class<?>[] support() {
        return new Class[] { Short.class, short.class };
    }

    @Override
    public Object parse(String value) {
        
        if (value == null) {
            return null;
        }
        
        try {
            return Short.valueOf(value);
        } catch (Exception e) {
            LOGGER.error(String.format("Fail to parse [%s] to short integer", value));
            return null;
        }
    }

}
