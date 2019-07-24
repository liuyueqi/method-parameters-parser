package com.liuyueqi.method.parameters.parser;

import com.liuyueqi.method.parameters.TypeInfo;
import com.liuyueqi.method.parameters.exception.ValueParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PrimitiveDoubleValueParser extends BaseValueParser {

    private static final Logger LOGGER = LoggerFactory.getLogger(PrimitiveDoubleValueParser.class);

    private static final TypeInfo[] SUPPORTED_TYPES = { TypeInfo.PRIMITIVE_DOUBLE };

    @Override
    public TypeInfo[] support() {
        return new TypeInfo[0];
    }

    @Override
    public Object parse(Object value) {

        if (value == null) {
            return 0D;
        }

        if (TypeInfo.PRIMITIVE_DOUBLE.getRawType().equals(value.getClass())) {
            return value;
        }

        try {
            return Double.parseDouble(value.toString());
        } catch (Exception e) {
            throw new ValueParseException(value, TypeInfo.PRIMITIVE_DOUBLE);
        }
    }
}
