package com.liuyueqi.method.parameters;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode(of = {"rawType", "genericTypes"})
@Getter
public class TypeInfo {

    public static final TypeInfo BOOLEAN = new TypeInfo(Boolean.class);
    public static final TypeInfo BYTE = new TypeInfo(Byte.class);
    public static final TypeInfo DOUBLE = new TypeInfo(Double.class);
    public static final TypeInfo INTEGER = new TypeInfo(Integer.class);
    public static final TypeInfo LONG = new TypeInfo(Long.class);
    public static final TypeInfo SHORT = new TypeInfo(Short.class);
    public static final TypeInfo STRING = new TypeInfo(String.class);

    public static final TypeInfo PRIMITIVE_BOOLEAN = new TypeInfo(boolean.class);
    public static final TypeInfo PRIMITIVE_BYTE = new TypeInfo(byte.class);
    public static final TypeInfo PRIMITIVE_DOUBLE = new TypeInfo(double.class);
    public static final TypeInfo PRIMITIVE_INTEGER = new TypeInfo(int.class);
    public static final TypeInfo PRIMITIVE_LONG = new TypeInfo(long.class);
    public static final TypeInfo PRIMITIVE_SHORT = new TypeInfo(short.class);

    private Class<?> rawType;
    private TypeInfo[] genericTypes;

    public TypeInfo(Class<?> rawType) {
        this(rawType, null);
    }

    public TypeInfo(Class<?> rawType, TypeInfo[] genericTypes) {
        
        if (rawType == null) {
            throw new IllegalArgumentException("Raw type of TypeInfo cannot be null");
        }
        this.rawType = rawType;
        if (genericTypes == null) {
            this.genericTypes = new TypeInfo[0];
        } else {
            this.genericTypes = genericTypes;
        }
    }

    public Object parseValue(String value) {
        return null;
    }
    
    @Override
    public String toString() {
        return this.rawType.toString();
    }
}
