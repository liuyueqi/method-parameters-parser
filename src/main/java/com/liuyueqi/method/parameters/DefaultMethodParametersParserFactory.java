package com.liuyueqi.method.parameters;

import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;

import com.liuyueqi.method.parameters.exception.ValueParseException;

public class DefaultMethodParametersParserFactory implements MethodParametersParserFactory {
    
    private static final ConcurrentHashMap<Method, DefaultMethodParametersParser> METHOD_PARAMETERS_PARSER_MAP = 
            new ConcurrentHashMap<Method, DefaultMethodParametersParser>();
    
    public static DefaultMethodParametersParserFactory getInstance() {
        return Holder.INSTANCE;
    }
    
    private DefaultMethodParametersParserFactory() {
    }

    @Override
    public MethodParametersParser getMethodParametersParser(Method method) {
        
        if (method == null) {
            throw new ValueParseException("Method cannot be null");
        }
        
        if (METHOD_PARAMETERS_PARSER_MAP.contains(method)) {
            return METHOD_PARAMETERS_PARSER_MAP.get(method);
        }
        
        DefaultMethodParametersParser parser = new DefaultMethodParametersParser(method);
        DefaultMethodParametersParser previousParser = METHOD_PARAMETERS_PARSER_MAP.putIfAbsent(method, parser);
        if (previousParser == null) {
            return parser;
        }
        return previousParser;
    }

    private static class Holder {
        private static final DefaultMethodParametersParserFactory INSTANCE = new DefaultMethodParametersParserFactory();
    }
}
