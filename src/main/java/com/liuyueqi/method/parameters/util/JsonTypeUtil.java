package com.liuyueqi.method.parameters.util;

public class JsonTypeUtil {
    
    public static final Class<?>[] BASE_TYPES = new Class[] { 
        Boolean.class,
        Byte.class,
        Double.class,
        Integer.class,
        Long.class,
        Short.class,
        String.class
    };

    public static boolean isBaseType(Class<?> type) {
        
        for (Class<?> baseType : BASE_TYPES) {
            if (baseType == type) {
                return true;
            }
        }
        return false;
    }
}
