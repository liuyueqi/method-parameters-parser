package com.liuyueqi.method.parameters.parser;

public interface ValueParser {

    Class<?>[] support();
    
    Object parse(String value);
}
