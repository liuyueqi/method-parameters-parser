package com.liuyueqi.method.parameters.parser.factory;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import com.liuyueqi.method.parameters.TypeInfo;
import com.liuyueqi.method.parameters.parser.ListValueParser;
import com.liuyueqi.method.parameters.parser.ValueParser;

@Deprecated
public class ListValueParserFactory implements ValueParserFactory {

    private static final ConcurrentHashMap<TypeInfo, ListValueParser> LIST_VALUE_PARSER_MAP = new ConcurrentHashMap<TypeInfo, ListValueParser>();

    private static final TypeInfo NO_GENERIC_TYPE = new TypeInfo(List.class);

    private ListValueParserFactory() {
        LIST_VALUE_PARSER_MAP.putIfAbsent(NO_GENERIC_TYPE, new ListValueParser());
    }

    public static ListValueParserFactory getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public ValueParser getValueParser(TypeInfo type) {

        if (type == null) {
            return LIST_VALUE_PARSER_MAP.get(NO_GENERIC_TYPE);
        }

        if (LIST_VALUE_PARSER_MAP.contains(type)) {
            return LIST_VALUE_PARSER_MAP.get(type);
        }

        ListValueParser parser = new ListValueParser(type);
        ListValueParser previousParser = LIST_VALUE_PARSER_MAP.putIfAbsent(type, parser);
        if (previousParser == null) {
            return parser;
        } else {
            return previousParser;
        }
    }

    private static class Holder {
        private static final ListValueParserFactory INSTANCE = new ListValueParserFactory();
    }

}
