package org.example.utils.json;


import org.example.csgsi.SelfState;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Optional;
import java.util.Stack;


public  class JsonParser {

    enum State {
        READING_KEY, READING_VALUE, WAITING_KEY, WAITING_VALUE
    }

    public static LinkedHashMap<String, JsonObject> getFields(String sJson) {

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

            if (state.equals(State.READING_VALUE) && (c == ',' || c == '}' || c=='{' || c=='\n' || c=='\r') && !inQuotes) {
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
                }else{
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
                        if(!value.isEmpty() && value.charAt(0) == '"'){
                            value.deleteCharAt(0);
                            jsonObject = new JsonObject(sKey, path, value.toString(), parent);
                        }
                        else {
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

    public static <T> T parseJson(HashMap<String, JsonObject> jsonMap, T instance) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException {
        Class<?> clazz = instance.getClass();
        JsonObject root = jsonMap.get("root");

        for (var field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            JsonField annotation = field.getAnnotation(JsonField.class);
            String fieldName = (annotation != null) ? annotation.value() : field.getName();
            Class<?> fieldType = field.getType();
            Object oldObject = field.get(instance);
            Object fieldValue = parseFieldValue(root, jsonMap, fieldName, oldObject, fieldType);
            setField(root,field,fieldValue,instance);
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
    private static Object parseFieldValue(JsonObject parent, HashMap<String, JsonObject> jsonMap, String fieldName, Object oldObject, Class<?> fieldType)
            throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {

        String key = parent.getPath() + "." + fieldName;
        JsonObject childJson = jsonMap.get(key);
        if (childJson == null) {
            return oldObject;
        }

        if (childJson.getChildren().isEmpty()) {
            if (fieldType.isEnum()) {
                return parseEnum(fieldName,fieldType,childJson.getValue());
            } else {
                return childJson.getValue();
            }
        } else {
            return updateOrCreate(childJson, jsonMap, oldObject, fieldType);
        }
    }
    @SuppressWarnings({"unchecked", "rawtypes"})
    private static Object parseEnum(String fieldName, Class<?> fieldType, Object value) {
        String jsonValue = value.toString();

        Class<? extends Enum<?>> enumClass = (Class<? extends Enum<?>>) fieldType.asSubclass(Enum.class);

        for (Enum<?> constant : enumClass.getEnumConstants()) {
            try {
                Field enumField = enumClass.getField(constant.name());
                JsonField annotation = enumField.getAnnotation(JsonField.class);
                if (annotation != null && annotation.value().equals(jsonValue)) {
                    return constant;
                }
            } catch (NoSuchFieldException e) {
                //
            }
        }

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
            setField(jsonObject,field,fieldValue,instance);
        }

        return instance;
    }
    public static HashMap<String, Optional<Method>> methods = new HashMap<>();

    private static void setField(JsonObject jsonObject, Field field, Object fieldValue, Object instance) throws IllegalAccessException {
        String methodName = "set" + capitalizeString(field.getName());
        String key = jsonObject.getPath() + "." + methodName;
        Class<?> clazz = instance.getClass();
        Optional<Method> cachedMethod = methods.get(key);
        if (cachedMethod == null) {
            try {
                Method method = clazz.getMethod(methodName, field.getType());
                methods.put(key, Optional.of(method));
                method.invoke(instance, fieldValue);
            } catch (NoSuchMethodException e) {
                field.setAccessible(true);
                field.set(instance, fieldValue);
                methods.put(key, Optional.empty());
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        } else if (cachedMethod.isEmpty()) {
            field.setAccessible(true);
            field.set(instance, fieldValue);
        } else {
            try {
                cachedMethod.get().invoke(instance, fieldValue);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static String capitalizeString(String str) {
        return  str.substring(0, 1).toUpperCase() + str.substring(1);
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


