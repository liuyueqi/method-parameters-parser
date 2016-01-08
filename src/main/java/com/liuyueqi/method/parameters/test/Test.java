package com.liuyueqi.method.parameters.test;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import com.liuyueqi.method.parameters.DefaultMethodParametersParserFactory;
import com.liuyueqi.method.parameters.MethodParametersParser;

public class Test {

    public static void main(String[] args) throws Exception {

        Class<?> clazz = TestService.class;
        Object instance = clazz.newInstance();
        
        Map<String, Method> methodMap = new HashMap<String, Method>();
        for (Method method : clazz.getMethods()) {
            methodMap.put(method.getName(), method);
        }
        
        DefaultMethodParametersParserFactory factory = DefaultMethodParametersParserFactory.getInstance();
        
        Method method = methodMap.get("test");
        MethodParametersParser parser = factory.getMethodParametersParser(method);

        method.invoke(instance, parser.parse("['aaa']"));
        method.invoke(instance, parser.parse("bbb"));
        method.invoke(instance, parser.parse("{'arg1': 'ccc'}"));
    }

}
