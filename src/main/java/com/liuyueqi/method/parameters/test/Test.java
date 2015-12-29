package com.liuyueqi.method.parameters.test;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import com.liuyueqi.method.parameters.parser.MethodParametersParser;

public class Test {

    public static void main(String[] args) throws Exception {

        Class<?> clazz = MethodParametersParser.class;
        Method[] methods = clazz.getMethods();
        Method method = null;
        for (Method m : methods) {
            if ("parse".equals(m.getName())) {
                method = m;
                break;
            }
        }
        
        Type[] genericParameterTypes = method.getGenericParameterTypes();
        for (Type type : genericParameterTypes) {
            
            if (type instanceof ParameterizedType) {
                ParameterizedType parameterizedType = (ParameterizedType) type;
                System.out.println("owner type: " + parameterizedType.getOwnerType());
                System.out.println("raw type: " + parameterizedType.getRawType());
                for (Type t : parameterizedType.getActualTypeArguments()) {
                    System.out.println(t);
                }
                System.out.println();
            }
        }
    }

}
