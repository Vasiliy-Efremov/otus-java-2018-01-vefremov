package ru.otus.l81;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.LinkedHashMap;

public class JsonConverter {

    public static String convertToJson(Object obj) throws IllegalAccessException {

        if (obj == null) {
            return null;
        }

        JSONObject jsonObject = new JSONObject(new LinkedHashMap());
        Field[] fieldsFromObject = getFieldsFromObject(obj);
        for (int i = 0; i < fieldsFromObject.length; i++) {
            fieldsFromObject[i].setAccessible(true);
            Object value = fieldsFromObject[i].get(obj);

            if (value.getClass().isArray()) {
                JSONArray jsonArray = new JSONArray();

                for (int j = 0; j < Array.getLength(value); j++) {
                    Object valueFromArray = Array.get(value, j);
                    jsonArray.add(valueFromArray);
                }
                jsonObject.put(fieldsFromObject[i].getName(), jsonArray);

            } else if (value instanceof Collection) {
                JSONArray jsonArray = new JSONArray();

                for (int j = 0; j < ((Collection) value).size(); j++) {
                    Object valueFromCollection = ((Collection) value).toArray()[j];
                    jsonArray.add(valueFromCollection);
                }
                jsonObject.put(fieldsFromObject[i].getName(), jsonArray);

            } else {
                jsonObject.put(fieldsFromObject[i].getName(), value);
            }
        }
        return jsonObject.toJSONString();
    }

    private static Field[] getFieldsFromObject(Object obj) {
        return obj.getClass().getDeclaredFields();
    }
}


