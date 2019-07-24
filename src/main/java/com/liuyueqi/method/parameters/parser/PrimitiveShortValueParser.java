package com.liuyueqi.method.parameters.parser;

import com.liuyueqi.method.parameters.TypeInfo;
import com.liuyueqi.method.parameters.exception.ValueParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PrimitiveShortValueParser extends BaseValueParser {

    private static final Logger LOGGER = LoggerFactory.getLogger(PrimitiveShortValueParser.class);

    private static final TypeInfo[] SUPPORTED_TYPES = { TypeInfo.PRIMITIVE_SHORT };

    @Override
    public TypeInfo[] support() {
        return new TypeInfo[0];
    }

    @Override
    public Object parse(Object value) {

        if (value == null) {
            return 0;
        }

        if (TypeInfo.PRIMITIVE_SHORT.getRawType().equals(value.getClass())) {
            return value;
        }

        try {
            return Short.parseShort(value.toString());
        } catch (Exception e) {
            throw new ValueParseException(value, TypeInfo.PRIMITIVE_SHORT);
        }
    }
}
