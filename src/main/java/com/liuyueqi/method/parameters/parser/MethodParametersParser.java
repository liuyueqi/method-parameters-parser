package com.liuyueqi.method.parameters.parser;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import com.liuyueqi.method.parameters.TypeInfo;

public class MethodParametersParser {

    private Method method;
    private TypeInfo[] parameterInfos;

    public MethodParametersParser(Method method) {
        this.method = method;
    }

    private void init() {

        Class<?>[] parameterTypes = this.method.getParameterTypes();
        if (parameterTypes == null || parameterTypes.length == 0) {
        }

        Type[] types = this.method.getGenericParameterTypes();
        if (types == null || types.length == 0) {
            return;
        }

        for (Type type : types) {

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

    public Object[] parse(List<TypeInfo> parameterInfos, Map<String, Long> map) {

        return null;
    }
}
