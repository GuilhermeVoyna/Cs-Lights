package org.example.utils.json;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class JsonParameter {

    private String key;
    private Object value;
    private String parent;

    public JsonParameter(String key, Object value, String parent) {
        this.key    = key;
        this.value  = value;
        this.parent = parent;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    enum State {
    READING_KEY, READING_VALUE,WAITING_KEY,WAITING_VALUE
}

    public static List<JsonParameter> toObject(String sJson) {

        List<JsonParameter> jsonParameters = new ArrayList<>();

        StringBuilder key       = new StringBuilder();
        StringBuilder value     = new StringBuilder();
        int braceLevel = 0;
        Stack<String> parents  = new Stack<>();
        parents.push("");
        State state = State.WAITING_KEY;

        for (int i = 0; i < sJson.length(); i++) {

            char c = sJson.charAt(i);
            if (c == ' ') {
                continue;
            }
            if (c == '\"' && state.equals(State.WAITING_KEY)) {
                state = State.READING_KEY;
            }
            if ((c == '\"' || isNumber(c)) && state.equals(State.WAITING_VALUE)) {
                state = State.READING_VALUE;
            }

            if (state.equals(State.READING_KEY)){
                if (c == ':'){
                    state = State.WAITING_VALUE;
                }
                key.append(c);
            }

            if (c == ',' || i == sJson.length()-1){
                jsonParameters.add(new JsonParameter(key.toString(),value.toString(),parents.peek() ));
                key.setLength(0);
                value.setLength(0);
                state = State.WAITING_KEY;
            }

                if(state.equals(State.READING_VALUE)) {
                    value.append(c);
                }
                if (c == '{' && !isReading(state)) {
                    braceLevel++;
                    jsonParameters.add(new JsonParameter(key.toString(),null,parents.peek() ));
                    parents.push(key.toString());
                    key.setLength(0);
                    value.setLength(0);
                    state = State.WAITING_KEY;
                } else if (c == '}'&& !isReading(state)) {
                    parents.pop();
                    braceLevel--;
                }
            }
        return jsonParameters;
    }

    private static boolean isReading(State state) {
        return state.equals(State.READING_KEY)||state.equals(State.READING_VALUE);
    }


    private static void insertHashMap(String sKey,String sValue, HashMap<String, Object> hashMap) {
        try{
            hashMap.put(sKey, Integer.parseInt(sValue));
        }catch (NumberFormatException e1){
            try {
                hashMap.put(sKey, Double.parseDouble(sValue));
            }catch (NumberFormatException e2){
                hashMap.put(sKey, sValue);
            }
        }

    }

    private static boolean isNumberOrChar(char c) {
        return isNumber(c)||isChar(c);
    }

    private static boolean isChar(char c) {
        return (c >= 'a' && c <= 'z') ||
                (c >= 'A' && c <= 'Z')|| c == ' ';
    }
    private static boolean isNumber(char c){
        return (c >= '0' && c <= '9');
    }
}
