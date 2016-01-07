package com.liuyueqi.method.parameters;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

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

    public TypeInfo(Class<?> rawType, TypeInfo[] genericTypes) {
        setRawType(rawType);
        setGenericTypes(genericTypes);
    }
    
    public TypeInfo(Type type) {
        
        if (type instanceof Class) {
            setRawType((Class<?>) type);
            setGenericTypes(null);
        } else if (type instanceof ParameterizedType) {
            
            ParameterizedType pt = (ParameterizedType) type;
            setRawType((Class<?>) pt.getRawType());
            
            Type[] actualTypeArguments = pt.getActualTypeArguments();
            TypeInfo[] genericTypes = new TypeInfo[actualTypeArguments.length];
            int index = 0;
            for (Type t : actualTypeArguments) {
                genericTypes[index++] = new TypeInfo(t);
            }
            setGenericTypes(genericTypes);
        }
    }
    
    private void setRawType(Class<?> rawType) {
        
        if (rawType == null) {
            throw new IllegalArgumentException("Raw type of TypeInfo cannot be null");
        }
        this.rawType = rawType;
    }
    
    private void setGenericTypes(TypeInfo[] genericTypes) {
        
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
