package com.liuyueqi.method.parameters.parser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.liuyueqi.method.parameters.util.JsonValueUtil;

public class PojoValueParser implements ValueParser {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(PojoValueParser.class);

    private Class<?> type;
    
    public PojoValueParser(Class<?> type) {
        
        if (type == null) {
            throw new IllegalArgumentException("Type for PojoValueParser is null");
        }
        this.type = type;
    }
    
    @Override
    public Class<?>[] support() {
        return new Class[] { this.type };
    }

    @Override
    public Object parse(String value) {
        
        if (!JsonValueUtil.isMap(value)) {
            LOGGER.error(String.format("%s cannot be parsed to type: %s", 
                    value, this.type.getSimpleName()));
        }
        return JSON.parseObject(value, this.type);
    }

}
