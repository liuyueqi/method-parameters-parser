package com.liuyueqi.method.parameters.parser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.liuyueqi.method.parameters.TypeInfo;
import com.liuyueqi.method.parameters.parser.factory.CommonValueParserFactory;
import com.liuyueqi.method.parameters.util.JsonValueUtils;

public class ListValueParser extends CollectionValueParser {

    private static final Logger LOGGER = LoggerFactory.getLogger(ListValueParser.class);

    public ListValueParser() {
        super();
    }

    public ListValueParser(TypeInfo genericType) {
        super(genericType);
    }

    @Override
    public TypeInfo[] support() {
        return new TypeInfo[0];
    }

    @Override
    public Object parse(Object value) {

        if (value == null) {
            return null;
        }
        
        if (value instanceof Collection) {
            return new ArrayList<Object>((Collection<?>) value);
        }

        if (value instanceof String) {
            return parseString((String) value);
        }
        
        throw new IllegalArgumentException("");
    }

    private Object parseString(String value) {

        value = value.trim();

        if (!JsonValueUtils.isArray(value)) {
            LOGGER.error(String.format("%s is not an array", value));
            return null;
        }

        JSONArray jsonArray = JSON.parseArray(value);
        if (jsonArray.isEmpty()) {
            return jsonArray;
        }
        
        TypeInfo genericType = getGenericType();
        if (genericType == null) {
            return jsonArray;
        }
        
        ValueParser parser = CommonValueParserFactory.getInstance().getValueParser(genericType);
        List<Object> result = new ArrayList<Object>(jsonArray.size());
        for (Object jsonObj : jsonArray) {
            result.add(parser.parse(jsonObj));
        }
        return result;
    }
}
