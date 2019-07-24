package com.liuyueqi.method.parameters;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;

import com.alibaba.fastjson.JSON;
import com.liuyueqi.method.parameters.exception.ValueParseException;
import com.liuyueqi.method.parameters.parser.CommonValueParserFactory;
import com.liuyueqi.method.parameters.parser.ValueParser;
import com.liuyueqi.method.parameters.parser.ValueParserFactory;
import com.liuyueqi.method.parameters.util.JsonValueUtils;
import com.liuyueqi.method.parameters.util.TypeInfoUtils;

public class DefaultMethodParametersParser implements MethodParametersParser {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultMethodParametersParser.class);

    private MethodParameterInfo[] methodParameterInfos;

    public DefaultMethodParametersParser(MethodParameterInfo[] methodParameterInfos) {

        if (methodParameterInfos == null) {
            throw new ValueParseException("");
        }
        this.methodParameterInfos = methodParameterInfos;
    }

    public DefaultMethodParametersParser(Method method) {

        if (method == null) {
            throw new ValueParseException("");
        }

        Type[] types = method.getGenericParameterTypes();
        if (types == null || types.length == 0) {
            this.methodParameterInfos = new MethodParameterInfo[0];
            return;
        }

        ParameterNameDiscoverer parameterNameDiscoverer = new LocalVariableTableParameterNameDiscoverer();
        String[] parameterNames = parameterNameDiscoverer.getParameterNames(method);

        this.methodParameterInfos = new MethodParameterInfo[types.length];
        int index = 0;
        for (Type type : types) {
            methodParameterInfos[index] = new MethodParameterInfo(parameterNames[index], new TypeInfo(type));
            index++;
        }
    }

    public Object[] parse(String value) {

        if (value == null) {
            if (this.methodParameterInfos.length == 1) {
                ValueParser parser = CommonValueParserFactory.getInstance().getValueParser(
                        methodParameterInfos[0].getType());
                return new Object[] { parser.parse(null) };
            }
            return null;
        }

        if (JsonValueUtils.isMap(value)) {
            return parseMap(JSON.parseObject(value));
        }

        if (JsonValueUtils.isArray(value)) {
            return parseArray(JSON.parseArray(value));
        }

        if (this.methodParameterInfos.length == 1 && TypeInfoUtils.isBaseType(this.methodParameterInfos[0].getType())) {

            ValueParser parser = CommonValueParserFactory.getInstance().getValueParser(
                    this.methodParameterInfos[0].getType());
            return new Object[] { parser.parse(value) };
        }

        throw new ValueParseException(String.format("Cannot parser \"%s\" to types: [%s]", value,
                String.format("%s", (Object[]) this.methodParameterInfos)));
    }

    private Object[] parseMap(Map<String, ?> value) {

        ValueParserFactory factory = CommonValueParserFactory.getInstance();

        try {
            
            if (this.methodParameterInfos.length == 1) {
                
                TypeInfo type = this.methodParameterInfos[0].getType();
                String name = this.methodParameterInfos[0].getName();
                
                if (TypeInfoUtils.isPojo(type)) {

                    ValueParser parser = factory.getValueParser(type);
                    
                    if (value.containsKey(name)) {
                        return new Object[] {parser.parse(value.get(name))};
                    } else {
                        return new Object[] { parser.parse(value) };
                    }
                }
            }
            
        } catch (ValueParseException e) {
            LOGGER.warn("Cannot parse {} to {}", value, this.methodParameterInfos[0].getType());
        }

        Object[] result = new Object[this.methodParameterInfos.length];
        int index = 0;
        for (MethodParameterInfo info : this.methodParameterInfos) {
            ValueParser parser = factory.getValueParser(info.getType());
            result[index++] = parser.parse(value.get(info.getName()));
        }
        return result;
    }

    private Object[] parseArray(List<?> array) {

        ValueParserFactory factory = CommonValueParserFactory.getInstance();

        if (this.methodParameterInfos.length == 1) {

            TypeInfo typeInfo = this.methodParameterInfos[0].getType();
            if (TypeInfoUtils.isList(typeInfo) || TypeInfoUtils.isSet(typeInfo)) {
                ValueParser parser = factory.getValueParser(typeInfo);
                return new Object[] { parser.parse(array) };
            }
        }

        if (array.size() != this.methodParameterInfos.length) {
            throw new ValueParseException("");
        }

        Object[] result = new Object[this.methodParameterInfos.length];
        int index = 0;
        for (Object item : array) {
            ValueParser parser = factory.getValueParser(this.methodParameterInfos[index].getType());
            result[index] = parser.parse(item);
            index++;
        }
        return result;
    }
}
