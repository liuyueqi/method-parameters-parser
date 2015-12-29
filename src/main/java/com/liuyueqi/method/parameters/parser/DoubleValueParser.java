package com.liuyueqi.method.parameters.parser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DoubleValueParser extends BaseTypeValueParser {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(DoubleValueParser.class);

    @Override
    public Class<?>[] support() {
        return new Class[] { Double.class, double.class };
    }

    @Override
    public Object parse(String value) {
        
        try {
            return Double.valueOf(value);
        } catch (Exception e) {
            LOGGER.error(String.format("Fail to parse [%s] to double float", value));
            return null;
        }
    }

}
