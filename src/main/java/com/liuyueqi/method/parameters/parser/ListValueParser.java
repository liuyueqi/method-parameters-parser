package com.liuyueqi.method.parameters.parser;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.liuyueqi.method.parameters.util.JsonTypeUtil;
import com.liuyueqi.method.parameters.util.JsonValueUtil;

public class ListValueParser extends CollectionValueParser {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(ListValueParser.class);

    public ListValueParser(Class<?> genericType) {
        super(genericType);
    }

    @Override
    public Class<?>[] support() {
        return new Class[] { List.class };
    }

    @Override
    public Object parse(String value) {
        
        if (value == null) {
            return null;
        }
        
        value = value.trim();
        if (!JsonValueUtil.isArray(value)) {
            LOGGER.error(String.format("%s is not an array", value));
            return null;
        }
        
        String content = value.substring(1, value.length() -1);
        String[] array = content.split(",");
        
        DefaultValueParserFactory factory = DefaultValueParserFactory.getInstance();
        if (JsonTypeUtil.isBaseType(getGenericType())) {
            BaseTypeValueParser parser = factory.getBaseTypeValueParser(getGenericType());
            List<Object> result = new ArrayList<Object>();
            for (String item : array) {
                result.add(parser.parse(item));
            }
            return result;
        }
        
        
        
        return null;
    }

}
