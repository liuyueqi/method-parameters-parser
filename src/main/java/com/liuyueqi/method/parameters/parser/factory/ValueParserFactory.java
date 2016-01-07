package com.liuyueqi.method.parameters.parser.factory;

import com.liuyueqi.method.parameters.TypeInfo;
import com.liuyueqi.method.parameters.parser.ValueParser;

public interface ValueParserFactory {

    ValueParser getValueParser(TypeInfo type);
}
