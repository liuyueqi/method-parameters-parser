package com.liuyueqi.method.parameters.parser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LongValueParser extends BaseTypeValueParser {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(LongValueParser.class);

    @Override
    public Class<?>[] support() {
        return new Class[] { Long.class, long.class };
    }

    @Override
    public Object parse(String value) {
        
        try {
            return Long.valueOf(value);
        } catch (Exception e) {
            LOGGER.error(String.format("Fail to parse [%s] to long integer", value));
            return null;
        }
    }

}
