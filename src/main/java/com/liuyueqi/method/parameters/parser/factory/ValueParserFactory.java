package com.liuyueqi.method.parameters.parser.factory;

import com.liuyueqi.method.parameters.TypeInfo;
import com.liuyueqi.method.parameters.parser.ValueParser;

public interface ValueParserFactory {

    ValueParser getValueParser(TypeInfo type);
    
    ValueParser getValueParser(TypeInfo type1, TypeInfo type2);
    
    ValueParser getValueParser(TypeInfo type1, TypeInfo type2, TypeInfo type3);
 
    ValueParser getValueParser(TypeInfo... types);
}

