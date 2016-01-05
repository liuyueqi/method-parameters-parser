package com.liuyueqi.method.parameters.parser;

import com.liuyueqi.method.parameters.TypeInfo;

public interface ValueParser {

    TypeInfo[] support();
    
    Object parse(Object value);
}
