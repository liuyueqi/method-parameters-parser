package com.liuyueqi.method.parameters.parser;

import com.liuyueqi.method.parameters.TypeInfo;
import com.liuyueqi.method.parameters.exception.ValueParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PrimitiveByteValueParser extends BaseValueParser {

    private static final Logger LOGGER = LoggerFactory.getLogger(PrimitiveByteValueParser.class);

    private static final TypeInfo[] SUPPORTED_TYPES = { TypeInfo.PRIMITIVE_BYTE };

    @Override
    public TypeInfo[] support() {
        return SUPPORTED_TYPES;
    }

    @Override
    public Object parse(Object value) {

        if (value == null) {
            return 0;
        }

        if (TypeInfo.PRIMITIVE_BYTE.getRawType().equals(value.getClass())) {
            return value;
        }

        try {
            return Byte.parseByte(value.toString());
        } catch (Exception e) {
            throw new ValueParseException(value, TypeInfo.PRIMITIVE_BYTE);
        }
    }
}
