package ru.otus.l81;

import java.lang.reflect.Field;

public class XUtils {

    public static boolean isPrimitive(Object obj) {
        if (obj instanceof Integer || obj instanceof Byte || obj instanceof Short || obj instanceof Boolean
                || obj instanceof Character || obj instanceof Float || obj instanceof Double) {
            return true;
        }
        return false;
    }

    public static Field[] getFieldsFromObject(Object obj) {
        return obj.getClass().getDeclaredFields();
    }

    public static boolean isString(Object obj) {
        if (obj instanceof String) {
            return true;
        }
        return false;
    }

    public static boolean isArray(Object obj) {
        if (obj.getClass().isArray()) {
            return true;
        }
        return false;
    }

    public static String handleArray(int[] obj) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < obj.length; i++) {
            stringBuilder.append(obj[i]);

            if (i + 1 < obj.length) {
                stringBuilder.append(",");
            }
        }
        return String.format("[%s]", stringBuilder);
    }
}
