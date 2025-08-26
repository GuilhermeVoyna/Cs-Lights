package org.example.utils.json;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class JsonObject {

    private String key;
    private Object value;
    private final JsonObject parent;
    private List<JsonObject> children = new ArrayList<>();

    public JsonObject(String key, Object value, JsonObject parent) {
        this.key = key;
        this.value = value;
        this.parent = parent;
        this.children = new ArrayList<>();
    }

    public List<JsonObject> getChildren() {
        return children;
    }
    public void addChildrem(JsonObject child) {
        this.children.add(child);
    }

    public String getKey() {
        return key;
    }

    enum State {
        READING_KEY, READING_VALUE,WAITING_KEY,WAITING_VALUE
    }

    public static HashMap<String ,JsonObject> toObject(String sJson) {

        boolean inQuotes        = false;
        StringBuilder key       = new StringBuilder();
        StringBuilder value     = new StringBuilder();
        int braceLevel = 0;

        HashMap<String ,JsonObject> json = new HashMap<>();
        JsonObject root = new JsonObject("root","root",null);
        Stack<JsonObject> parents  = new Stack<>();
        parents.push(root);
        json.put("root",root);
        State state = State.WAITING_KEY;

        for (int i = 0; i < sJson.length(); i++) {


            char c = sJson.charAt(i);

            if ((c == ' ') && !inQuotes) {
                if (state.equals(State.READING_VALUE)){
                    state = State.WAITING_KEY;
                }
                continue;
            }

            if (c == '"'){
                inQuotes = !inQuotes;
            }

            if (inQuotes && state.equals(State.WAITING_KEY)) {
                state = State.READING_KEY;
            }
            if ((inQuotes || isNumberOrChar(c)) && state.equals(State.WAITING_VALUE)) {
                state = State.READING_VALUE;
            }

            if (state.equals(State.READING_KEY)){
                if (!inQuotes){
                    state = State.WAITING_VALUE;
                } else {
                    if (c != '"'){
                        key.append(c);
                    }
                }
            }
            if(state.equals(State.READING_VALUE)) {
                if (!inQuotes && (c == '\n' ) || !inQuotes && (c == '"') ){
                    state = State.WAITING_KEY;
                }else
                if (c != '"') {
                    value.append(c);
                }

            }

            if  ((c == ',' && isWaiting(state))){

                insertJsonObject(key, value, json, parents.peek());
                key.setLength(0);
                value.setLength(0);
                state = State.WAITING_KEY;

            }

            if (c == '{' && isWaiting(state)) {
                if(braceLevel > 0) {
                    insertJsonObject(key, value, json, parents.peek());
                    parents.push(new JsonObject(key.toString(),null,parents.peek()));
                    key.setLength(0);
                    value.setLength(0);
                    state = State.WAITING_KEY;
                }
                braceLevel++;

            }

            if (c == '}'&& isWaiting(state)){
                braceLevel --;
                insertJsonObject(key, value, json, parents.peek());
                state = State.WAITING_KEY;
                parents.pop();
                key.setLength(0);
                value.setLength(0);

            }
        }
        json.remove("");
        return json;
    }

    private static boolean isWaiting(State state) {
        return !state.equals(State.READING_KEY) && !state.equals(State.READING_VALUE);
    }


    private static void insertJsonObject(StringBuilder key,StringBuilder value,HashMap<String ,JsonObject> json,JsonObject parent ) {

            JsonObject jsonObject;
            switch (value.toString()) {
                case "null":
                    jsonObject = new JsonObject(key.toString(),null,parent);
                    break;
                case "true":
                    jsonObject = new JsonObject(key.toString(),true,parent);
                    break;
                case "false":
                    jsonObject = new JsonObject(key.toString(),false,parent);
                    break;

                default:
                    try {
                        jsonObject = new JsonObject(key.toString(),Integer.parseInt(value.toString()),parent);
                    } catch (NumberFormatException e1) {
                        try {
                            jsonObject = new JsonObject(key.toString(),Double.parseDouble(value.toString()),parent);
                        } catch (NumberFormatException e2) {
                            jsonObject = new JsonObject(key.toString(),value.toString(),parent);
                        }
                    }
            }
        json.get(parent.getKey()).addChildrem(jsonObject);
        json.put(key.toString(),jsonObject);
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
