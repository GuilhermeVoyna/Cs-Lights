package org.example.utils.json;


import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;



public class JsonParser {

    enum State {
        READING_KEY, READING_VALUE, WAITING_KEY, WAITING_VALUE
    }

    private static LinkedHashMap<String, JsonObject> jsonMap(String sJson) {

        boolean inQuotes = false;
        StringBuilder key = new StringBuilder();
        StringBuilder value = new StringBuilder();
        int braceLevel = 0;

        LinkedHashMap<String, JsonObject> json = new LinkedHashMap<>();
        JsonObject root = new JsonObject("root", "root", "root", null);
        Stack<JsonObject> parents = new Stack<>();
        parents.push(root);
        json.put("root", root);
        State state = State.WAITING_KEY;

        for (int i = 0; i < sJson.length(); i++) {


            char c = sJson.charAt(i);

            if ((c == ' ') && !inQuotes) {
                continue;
            }
            if (state.equals(State.READING_VALUE) && !isNumber(c) && !inQuotes) {
                state = State.WAITING_KEY;
            }

            if (c == '"') {
                inQuotes = !inQuotes;
            }

            if (inQuotes && state.equals(State.WAITING_KEY)) {
                state = State.READING_KEY;
            }
            if ((inQuotes || isNumberOrChar(c)) && state.equals(State.WAITING_VALUE)) {
                state = State.READING_VALUE;
            }

            if (state.equals(State.READING_KEY)) {
                if (!inQuotes) {
                    state = State.WAITING_VALUE;
                } else {
                    if (c != '"') {
                        key.append(c);
                    }
                }
            }
            if (state.equals(State.READING_VALUE)) {
                if (!inQuotes && (c == '\n') || !inQuotes && (c == '"')) {
                    state = State.WAITING_KEY;
                } else if (c != '"') {
                    value.append(c);
                }

            }

            if ((c == ',' && isWaiting(state))) {

                insertJsonObject(key, value, json, parents);
                key.setLength(0);
                value.setLength(0);
                state = State.WAITING_KEY;

            }

            if (c == '{' && isWaiting(state)) {
                if (braceLevel > 0) {
                    insertJsonObject(key, value, json, parents,true);
                    key.setLength(0);
                    value.setLength(0);
                    state = State.WAITING_KEY;
                }
                braceLevel++;

            }

            if (c == '}' && isWaiting(state)) {
                braceLevel--;
                insertJsonObject(key, value, json, parents);
                state = State.WAITING_KEY;
                parents.pop();
                key.setLength(0);
                value.setLength(0);

            }
        }
        return json;
    }

    private static boolean isWaiting(State state) {
        return !state.equals(State.READING_KEY) && !state.equals(State.READING_VALUE);
    }
    private static void insertJsonObject(StringBuilder key, StringBuilder value, HashMap<String, JsonObject> json, Stack<JsonObject> parents) {
        insertJsonObject(key,value,json,parents,false);
    }
    private static void insertJsonObject(StringBuilder key, StringBuilder value, HashMap<String, JsonObject> json, Stack<JsonObject> parents,boolean insertParent) {
        if (!key.isEmpty() || !value.isEmpty()) {
            JsonObject parent = parents.peek();
            JsonObject jsonObject;
            String sKey = key.toString();
            String path = json.get(parent.getPath()).getPath() + '.' + sKey;
            switch (value.toString()) {
                case "null":
                    jsonObject = new JsonObject(sKey, path, null, parent);
                    break;
                case "true":
                    jsonObject = new JsonObject(sKey, path, true, parent);
                    break;
                case "false":
                    jsonObject = new JsonObject(sKey, path, false, parent);
                    break;

                default:
                    try {
                        jsonObject = new JsonObject(sKey, path, Integer.parseInt(value.toString()), parent);
                    } catch (NumberFormatException e1) {
                        try {
                            jsonObject = new JsonObject(sKey, path, Double.parseDouble(value.toString()), parent);
                        } catch (NumberFormatException e2) {
                            jsonObject = new JsonObject(sKey, path, value.toString(), parent);
                        }
                    }
            }
            if(insertParent){
                parents.push(jsonObject);
            }
            json.get(parent.getPath()).addChild(jsonObject);
            json.put(path, jsonObject);
        }
    }

    private static boolean isNumberOrChar(char c) {
        return isNumber(c) || isChar(c);
    }

    private static boolean isChar(char c) {
        return (c >= 'a' && c <= 'z') ||
                (c >= 'A' && c <= 'Z') || c == ' ';
    }

    private static boolean isNumber(char c) {
        return (c >= '0' && c <= '9');
    }

    public static <T> T parseJson(String sJson, T instance) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException {
        HashMap<String, JsonObject> jsonMap = jsonMap(sJson);
        Class<?> clazz = instance.getClass();
        JsonObject root = jsonMap.get("root");

        for (var field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            JsonField annotation = field.getAnnotation(JsonField.class);
            String fieldName = (annotation != null) ? annotation.value() : field.getName();
            Class<?> fieldType = field.getType();
            Object actualObject = field.get(instance);
            Object fieldValue = parseFieldValue(root, jsonMap, fieldName, actualObject, fieldType);
            setField(field,fieldValue,instance);
        }
        return instance;
    }

    private static Object updateOrCreate(JsonObject jsonObject, HashMap<String, JsonObject> jsonMap, Object oldChildInstance, Class<?> childClass) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException {
        if (oldChildInstance != null){
            return updateInstance(jsonObject, jsonMap,oldChildInstance );
        }
        else {
            return newInstance(jsonObject, jsonMap, childClass);
        }
    }
    private static Object parseFieldValue(JsonObject parent, HashMap<String, JsonObject> jsonMap, String fieldName, Object currentValue, Class<?> fieldType)
            throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {

        String key = parent.getPath() + "." + fieldName;
        JsonObject childJson = jsonMap.get(key);
        if (childJson == null) {
            return null;
        }

        if (childJson.getChildren().isEmpty()) {
            if (fieldType.isEnum()) {
                return parseEnum(fieldType,childJson.getValue());
            } else {
                return childJson.getValue();
            }
        } else {
            return updateOrCreate(childJson, jsonMap, currentValue, fieldType);
        }
    }
    @SuppressWarnings({"unchecked", "rawtypes"})
    private static Object parseEnum(Class<?> fieldType, Object value) {
    return Enum.valueOf((Class<Enum>) fieldType.asSubclass(Enum.class), value.toString());
    }


    private static Object updateInstance(JsonObject jsonObject, HashMap<String, JsonObject> jsonMap, Object instance)
            throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException {

        Class<?> clazz = instance.getClass();
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);
            JsonField annotation = field.getAnnotation(JsonField.class);
            String fieldName = (annotation != null) ? annotation.value() : field.getName();

            Class<?> fieldType = field.getType();
            Object actualObject = field.get(instance);

            Object fieldValue = parseFieldValue(jsonObject, jsonMap, fieldName, actualObject, fieldType);
            setField(field,fieldValue,instance);
        }

        return instance;
    }
    private static void setField(Field field, Object fieldValue, Object instance) throws IllegalAccessException {
                field.setAccessible(true);
                field.set(instance, fieldValue);
    }

    private static Object newInstance(JsonObject jsonObject, HashMap<String, JsonObject> jsonMap, Class<?> clazz)
            throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        Field[] fields = clazz.getDeclaredFields();
        int fieldCount = fields.length;
        Object[] arguments = new Object[fieldCount];
        Class<?>[] parameterTypes = new Class<?>[fieldCount];

        for (int i = 0; i < fieldCount; i++) {
            Field field = fields[i];
            field.setAccessible(true);
            JsonField annotation = field.getAnnotation(JsonField.class);
            String fieldName = (annotation != null) ? annotation.value() : field.getName();
            Class<?> fieldType = field.getType();
            arguments[i]        =  parseFieldValue(jsonObject, jsonMap, fieldName, null, fieldType);
            parameterTypes[i]   = fieldType;
        }

        Constructor<?> constructor = clazz.getDeclaredConstructor(parameterTypes);
        constructor.setAccessible(true);
        return constructor.newInstance(arguments);
    }

}


