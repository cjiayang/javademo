package net.jyou.javase.jmx;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;

/**
 * Utility methods used whenever we are processing JMX client information.
 *
 * @author graywatson
 */
public class ClientUtils {

    /**
     * Convert a string to an object based on the type string.
     */
    public static Object stringToParam(String string, String typeString) throws IllegalArgumentException {
        if (typeString.equals("boolean") || typeString.equals("java.lang.Boolean")) {
            return Boolean.parseBoolean(string);
        } else if (typeString.equals("char") || typeString.equals("java.lang.Character")) {
            if (string.length() == 0) {
                // not sure what to do here
                return '\0';
            } else {
                return string.toCharArray()[0];
            }
        } else if (typeString.equals("byte") || typeString.equals("java.lang.Byte")) {
            return Byte.parseByte(string);
        } else if (typeString.equals("short") || typeString.equals("java.lang.Short")) {
            return Short.parseShort(string);
        } else if (typeString.equals("int") || typeString.equals("java.lang.Integer")) {
            return Integer.parseInt(string);
        } else if (typeString.equals("long") || typeString.equals("java.lang.Long")) {
            return Long.parseLong(string);
        } else if (typeString.equals("java.lang.String")) {
            return string;
        } else if (typeString.equals("float") || typeString.equals("java.lang.Float")) {
            return Float.parseFloat(string);
        } else if (typeString.equals("double") || typeString.equals("java.lang.Double")) {
            return Double.parseDouble(string);
        } else {
            Constructor<?> constr = getConstructor(typeString);
            try {
                return constr.newInstance(new Object[] { string });
            } catch (Exception e) {
                throw new IllegalArgumentException(
                        "Could not get new instance using string constructor for type " + typeString);
            }
        }
    }

    /**
     * Return the string version of value.
     */
    public static String valueToString(Object value) {
        if (value == null) {
            return "null";
        } else if (!value.getClass().isArray()) {
            return value.toString();
        }

        StringBuilder sb = new StringBuilder();
        valueToString(sb, value);
        return sb.toString();
    }


    private static <C> Constructor<C> getConstructor(String typeString) throws IllegalArgumentException {
        Class<Object> clazz;
        try {
            @SuppressWarnings("unchecked")
            Class<Object> clazzCast = (Class<Object>) Class.forName(typeString);
            clazz = clazzCast;
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException("Unknown class for type " + typeString);
        }
        try {
            @SuppressWarnings("unchecked")
            Constructor<C> constructor = (Constructor<C>) clazz.getConstructor(new Class[] { String.class });
            return constructor;
        } catch (Exception e) {
            throw new IllegalArgumentException("Could not find constructor with single String argument for " + clazz);
        }
    }

    private static void valueToString(StringBuilder sb, Object value) {
        if (value == null) {
            sb.append("null");
            return;
        } else if (!value.getClass().isArray()) {
            sb.append(value);
            return;
        }

        sb.append('[');
        int length = Array.getLength(value);
        for (int i = 0; i < length; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            valueToString(sb, Array.get(value, i));
        }
        sb.append(']');
    }
}
