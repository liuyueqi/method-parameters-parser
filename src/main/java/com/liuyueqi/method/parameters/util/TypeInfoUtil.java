package com.liuyueqi.method.parameters.util;

import com.liuyueqi.method.parameters.TypeInfo;

public class TypeInfoUtil {

    public static final Class<?>[] BASE_TYPES = new Class[] { Boolean.class, Byte.class, Double.class, Integer.class,
            Long.class, Short.class, String.class };

    public static boolean isBaseType(TypeInfo type) {

        if (type == null) {
            return false;
        }
        
        for (Class<?> baseType : BASE_TYPES) {
            if (baseType == type.getRawType()) {
                return true;
            }
        }
        
        return false;
    }
}
