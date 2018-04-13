package ru.otus.l81;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Collection;

public class XUtils {

    public static boolean isPrimitive(Object obj) {
        if (obj instanceof Integer || obj instanceof Byte || obj instanceof Short || obj instanceof Boolean
                || obj instanceof Character || obj instanceof Float || obj instanceof Double || obj instanceof Long) {
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

    public static boolean isCollection(Object obj) {
        if (obj instanceof Collection<?>) {
            return true;
        }
        return false;
    }

    public static JSONArray handleCollection(Object obj) throws IllegalAccessException {
        JSONArray jsonArray = new JSONArray();

        for (Object element : (Collection)obj) {
            if (isString(element)) {
                jsonArray.add(element);
            }
            else if (isPrimitive(element)) {
                jsonArray.add(element);
            }
            else if (isArray(element)) {
                jsonArray.add(handleArray(element));
            }
            else if (isCollection(element)) {
                jsonArray.add(handleCollection(element));
            }
            else {
                jsonArray.add(handleObject(element));
            }
        }
        return jsonArray;
    }

    public static JSONArray handleArray(Object fieldValue) throws IllegalAccessException {
        JSONArray jsonArray = new JSONArray();

        for (int i = 0; i < Array.getLength(fieldValue); i++) {
            Object value = Array.get(fieldValue, i);

            if (isString(value)) {
                jsonArray.add(value);
            }
            else if (isPrimitive(value)) {
                jsonArray.add(value);
            }
            else if (isArray(value)) {
                jsonArray.add(handleArray(value));
            }
            else if (isCollection(value)) {
                jsonArray.add(handleCollection(value));
            }
            else {
                jsonArray.add(handleObject(value));
            }
        }
        return jsonArray;
    }

    public static JSONObject handleObject(Object obj) throws IllegalAccessException {
        JSONObject jsonObject = new JSONObject();

        for (Field field : XUtils.getFieldsFromObject(obj)) {
            field.setAccessible(true);
            Object value = field.get(obj);

            if (isString(value)) {
                jsonObject.put(field.getName(), value);
            }
            else if (isPrimitive(value)) {
                jsonObject.put(field.getName(), value);
            }
            else if (isArray(value)) {
                jsonObject.put(field.getName(), handleArray(value));
            }
            else if (isCollection(value)) {
                jsonObject.put(field.getName(), handleCollection(value));
            }
            else {
                jsonObject.put(field.getName(), handleObject(value));
            }
        }
        return jsonObject;
    }
}
