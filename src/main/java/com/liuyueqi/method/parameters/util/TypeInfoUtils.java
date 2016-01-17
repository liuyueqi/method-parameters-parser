package com.liuyueqi.method.parameters.util;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.liuyueqi.method.parameters.TypeInfo;

public class TypeInfoUtils {

    public static final Class<?>[] BASE_TYPES = new Class[] { Boolean.class, Byte.class, Double.class, Integer.class,
            Long.class, Short.class, String.class, boolean.class, byte.class, double.class, int.class, long.class,
            short.class };

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
    
    public static boolean isList(TypeInfo type) {
        
        if (type == null) {
            return false;
        }
        
        return (List.class == type.getRawType());
    }
    
    public static boolean isSet(TypeInfo type) {
        
        if (type == null) {
            return false;
        }
        
        return (Set.class == type.getRawType());
    }
    
    public static boolean isMap(TypeInfo type) {
        
        if (type == null) {
            return false;
        }
        
        return (Map.class == type.getRawType());
    }
    
    public static boolean isPojo(TypeInfo type) {
        return !(isBaseType(type) || isList(type) || isSet(type) || isMap(type));
    }
}
