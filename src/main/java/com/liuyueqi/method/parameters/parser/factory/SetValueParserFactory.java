package com.liuyueqi.method.parameters.parser.factory;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import com.liuyueqi.method.parameters.TypeInfo;
import com.liuyueqi.method.parameters.parser.SetValueParser;
import com.liuyueqi.method.parameters.parser.ValueParser;

public class SetValueParserFactory implements ValueParserFactory {

    private static final ConcurrentHashMap<TypeInfo, SetValueParser> SET_VALUE_PARSER_MAP = 
            new ConcurrentHashMap<TypeInfo, SetValueParser>();
    
    private static final TypeInfo NO_GENERIC_TYPE = new TypeInfo(Set.class);
    
    private SetValueParserFactory() {
        SET_VALUE_PARSER_MAP.putIfAbsent(NO_GENERIC_TYPE, new SetValueParser());
    }
    
    public static SetValueParserFactory getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public ValueParser getValueParser(TypeInfo type) {
        
        if (type == null) {
            return SET_VALUE_PARSER_MAP.get(NO_GENERIC_TYPE);
        }

        if (SET_VALUE_PARSER_MAP.contains(type)) {
            return SET_VALUE_PARSER_MAP.get(type);
        }

        SetValueParser parser = new SetValueParser(type);        
        SetValueParser previousParser = SET_VALUE_PARSER_MAP.putIfAbsent(type, parser);
        if (previousParser == null) {
            return parser;
        } else {
            return previousParser;
        }
    }

    private static class Holder {
        private static final SetValueParserFactory INSTANCE = new SetValueParserFactory();
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
