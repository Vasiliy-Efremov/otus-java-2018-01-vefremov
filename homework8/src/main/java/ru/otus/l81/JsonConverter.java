package ru.otus.l81;

public class JsonConverter {
    private static Object object;

    public JsonConverter(Object object) {
        this.object = object;
    }

    public static String convertToJson(Object obj) throws IllegalAccessException {
        if (obj == null) {
            return "null";
        }
        else if (XUtils.isString(obj)) {
            return String.format("\"%s\"", obj);
        }
        else if (XUtils.isPrimitive(obj)) {
            return obj.toString();
        }
        else if (XUtils.isCollection(obj)) {
            return XUtils.handleCollection(obj).toJSONString();
        }
        else if (XUtils.isArray(obj)) {
            return XUtils.handleArray(obj).toJSONString();
        }
        else {
            return XUtils.handleObject(obj).toJSONString();
        }
    }
}
