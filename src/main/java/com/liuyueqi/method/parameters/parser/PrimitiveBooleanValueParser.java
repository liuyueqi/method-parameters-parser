package com.liuyueqi.method.parameters.parser;

import com.liuyueqi.method.parameters.TypeInfo;
import com.liuyueqi.method.parameters.exception.ValueParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PrimitiveBooleanValueParser extends BaseValueParser {

    private static final Logger LOGGER = LoggerFactory.getLogger(PrimitiveBooleanValueParser.class);

    private static final TypeInfo[] SUPPORTED_TYPES = { TypeInfo.PRIMITIVE_BOOLEAN };

    @Override
    public TypeInfo[] support() {
        return SUPPORTED_TYPES;
    }

    @Override
    public Object parse(Object value) {

        if (value == null) {
            return false;
        }

        if (TypeInfo.PRIMITIVE_BOOLEAN.getRawType().equals(value.getClass())) {
            return value;
        }

        if ("true".equalsIgnoreCase(value.toString())) {
            return true;
        }

        if ("false".equalsIgnoreCase(value.toString())) {
            return false;
        }

        throw new ValueParseException(value, TypeInfo.PRIMITIVE_BOOLEAN);
    }
}
