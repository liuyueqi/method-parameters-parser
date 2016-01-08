package com.liuyueqi.method.parameters;

import lombok.Getter;

import org.apache.commons.lang3.StringUtils;

import com.liuyueqi.method.parameters.exception.ValueParseException;

@Getter
public class MethodParameterInfo {

    private String name;
    private TypeInfo type;

    public MethodParameterInfo(String name, TypeInfo type) {
        
        if (StringUtils.isBlank(name)) {
            throw new ValueParseException("");
        }
        if (type == null) {
            throw new ValueParseException("");
        }
        
        this.name = name;
        this.type = type;
    }
    
    public Object parse(String value) {
        
        return null;
    }
    
    @Override
    public String toString() {
        return "(" + type + " " + name + ")";
    }
}
