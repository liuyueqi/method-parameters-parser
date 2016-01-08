package com.liuyueqi.method.parameters;

import java.lang.reflect.Method;

public interface MethodParametersParserFactory {

    MethodParametersParser getMethodParametersParser(Method method);
}
