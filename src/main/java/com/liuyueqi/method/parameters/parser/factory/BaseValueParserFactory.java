package com.liuyueqi.method.parameters.parser.factory;

import java.util.HashMap;
import java.util.Map;

import com.liuyueqi.method.parameters.TypeInfo;
import com.liuyueqi.method.parameters.parser.BaseValueParser;
import com.liuyueqi.method.parameters.parser.BooleanValueParser;
import com.liuyueqi.method.parameters.parser.ByteValueParser;
import com.liuyueqi.method.parameters.parser.DoubleValueParser;
import com.liuyueqi.method.parameters.parser.IntegerValueParser;
import com.liuyueqi.method.parameters.parser.LongValueParser;
import com.liuyueqi.method.parameters.parser.ShortValueParser;
import com.liuyueqi.method.parameters.parser.StringValueParser;
import com.liuyueqi.method.parameters.parser.ValueParser;

public class BaseValueParserFactory implements ValueParserFactory {
    
    private static final Map<TypeInfo, BaseValueParser> BASE_VALUE_PARSER_MAP = 
            new HashMap<TypeInfo, BaseValueParser>();
    
    static {
        BASE_VALUE_PARSER_MAP.put(TypeInfo.BOOLEAN, new BooleanValueParser());
        BASE_VALUE_PARSER_MAP.put(TypeInfo.BYTE, new ByteValueParser());
        BASE_VALUE_PARSER_MAP.put(TypeInfo.DOUBLE, new DoubleValueParser());
        BASE_VALUE_PARSER_MAP.put(TypeInfo.INTEGER, new IntegerValueParser());
        BASE_VALUE_PARSER_MAP.put(TypeInfo.LONG, new LongValueParser());
        BASE_VALUE_PARSER_MAP.put(TypeInfo.SHORT, new ShortValueParser());
        BASE_VALUE_PARSER_MAP.put(TypeInfo.STRING, new StringValueParser());
    }
    
    private BaseValueParserFactory() {
    }
    
    public static BaseValueParserFactory getInstance() {
        return Holder.INSTANCE;
    }
    
    @Override
    public ValueParser getValueParser(TypeInfo type) {
        
        BaseValueParser parser = BASE_VALUE_PARSER_MAP.get(type);
        if (parser == null) {
            throw new IllegalArgumentException(String.format(
                    "Cannot find BaseValueParser for type: %s", type));
        }
        return parser;
    }

    private static class Holder {
        private static final BaseValueParserFactory INSTANCE = new BaseValueParserFactory();
    }

    @Override
    public ValueParser getValueParser(TypeInfo type1, TypeInfo type2) {
        return null;
    }

    @Override
    public ValueParser getValueParser(TypeInfo type1, TypeInfo type2, TypeInfo type3) {
        return null;
    }

    @Override
    public ValueParser getValueParser(TypeInfo... types) {
        return null;
    }
}
