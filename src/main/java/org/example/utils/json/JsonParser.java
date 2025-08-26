package org.example.utils.json;

import java.util.HashMap;

public class JsonParser {

    public static <T> String toJson(T instance){
        return "Todo";
    }
    /*
    * Converts a json into an object
    * */
   public static <T> T fromJson(String sJson,Class<T> tClass){
      HashMap<String,JsonObject> json = JsonObject.toObject(sJson);
      return null;
   }

}
