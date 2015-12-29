package com.liuyueqi.method.parameters.parser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IntegerValueParser extends BaseTypeValueParser {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IntegerValueParser.class);

    @Override
    public Class<?>[] support() {
        return new Class[] { Integer.class, int.class };
    }

    @Override
    public Object parse(String value) {
        
        try {
            return Integer.valueOf(value);
        } catch (Exception e) {
            LOGGER.error(String.format("Fail to parse [%s] to integer", value));
            return null;
        }
    }

}
