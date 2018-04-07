package ru.otus.l81;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.LinkedHashMap;

public class JsonConverter {

    private static Object object;
    private static int[] arrayWithInts;
    private static JSONObject jsonObject = new JSONObject(new LinkedHashMap());

    public JsonConverter(int[] arrayWithInts) {
        this.arrayWithInts = arrayWithInts;
    }

    public JsonConverter(Object object) {
        this.object = object;
    }

    public static void setObject(Object object) {
        JsonConverter.object = object;
    }

    public static void setArrayWithInts(int[] arrayWithInts) {
        JsonConverter.arrayWithInts = arrayWithInts;
    }


    public static String convertToJson() throws IllegalAccessException {

        if (object == null && arrayWithInts != null) {
            return XUtils.handleArray(arrayWithInts);
        }

        else {

            if (object == null) {
                return "null";
            }

            else if (XUtils.isPrimitive(object)) {
                return object.toString();
            }

            else if (XUtils.isString(object)) {
                return String.format("\"%s\"", object);
            }

            else if (XUtils.isArray(object)) {
                return XUtils.handleArray((arrayWithInts));
            }

            else {
                Field[] fieldsFromObject = XUtils.getFieldsFromObject(object);

                for (int i = 0; i < fieldsFromObject.length; i++) {
                    fieldsFromObject[i].setAccessible(true);
                    Object value = fieldsFromObject[i].get(object);

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
            }
            return jsonObject.toJSONString();
        }
    }
}


