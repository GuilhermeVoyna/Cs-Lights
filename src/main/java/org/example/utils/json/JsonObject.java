package org.example.utils.json;

import java.util.ArrayList;
import java.util.List;

public class JsonObject {

    private final Object value;
    private List<JsonObject> children = new ArrayList<>();
    private final JsonObject parent;
    private final String keyValue;
    private final String path;


    public JsonObject(String keyValue,String path, Object value, JsonObject parent) {
        this.keyValue = keyValue;
        this.path = path;
        this.value = value;
        this.parent = parent;
        this.children = new ArrayList<>();
    }

    public List<JsonObject> getChildren() {
        return children;
    }
    public void addChild(JsonObject child) {
        this.children.add(child);
    }

    public String getPath() {
        return path;
    }

    public JsonObject getParent() {
        return parent;
    }

    public String getKeyValue() {
        return keyValue;
    }

    public Object getValue() {
        return value;
    }
}
