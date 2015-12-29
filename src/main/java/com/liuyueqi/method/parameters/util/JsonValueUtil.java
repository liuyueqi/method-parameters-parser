package com.liuyueqi.method.parameters.util;

public class JsonValueUtil {

    public static boolean isArray(String value) {
        
        if (value == null) {
            return false;
        }
        
        int length = value.length();
        return (value.charAt(0) == '[' && value.charAt(length - 1) == ']');
    }
    
    public static boolean isMap(String value) {
        
        if (value == null) {
            return false;
        }
        
        int length = value.length();
        return (value.charAt(0) == '{' && value.charAt(length - 1) == '}');
    }
}
