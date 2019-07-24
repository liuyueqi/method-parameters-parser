package com.liuyueqi.method.parameters.parser;

import com.liuyueqi.method.parameters.TypeInfo;
import com.liuyueqi.method.parameters.exception.ValueParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PrimitiveLongValueParser extends BaseValueParser {

    private static final Logger LOGGER = LoggerFactory.getLogger(PrimitiveLongValueParser.class);

    private static final TypeInfo[] SUPPORTED_TYPES = { TypeInfo.PRIMITIVE_LONG };

    @Override
    public TypeInfo[] support() {
        return new TypeInfo[0];
    }

    @Override
    public Object parse(Object value) {

        if (value == null) {
            return 0L;
        }

        if (TypeInfo.PRIMITIVE_LONG.getRawType().equals(value.getClass())) {
            return value;
        }

        try {
            return Long.parseLong(value.toString());
        } catch (Exception e) {
            throw new ValueParseException(value, TypeInfo.PRIMITIVE_LONG);
        }
    }
}
