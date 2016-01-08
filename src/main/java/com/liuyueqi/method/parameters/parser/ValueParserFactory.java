package com.liuyueqi.method.parameters.parser;

import com.liuyueqi.method.parameters.TypeInfo;

public interface ValueParserFactory {

    ValueParser getValueParser(TypeInfo type);
}
