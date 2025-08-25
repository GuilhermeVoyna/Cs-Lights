package org.example.utils.json;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class JsonObject {
enum State {
    READING_KEY, READING_VALUE
}
    public static HashMap<String, Object> toObject(String sJson) {
        HashMap<String, Object> hashMap = new HashMap<>();

        StringBuilder key = new StringBuilder();
        StringBuilder value = new StringBuilder();
        boolean afterPoints    = false;
        boolean insideQuotes = false;
        String sObjJson;

        int begin=0;
        int end;

        State state = State.READING_KEY;

        for (int i = 0; i < sJson.length(); i++) {
            char c = sJson.charAt(i);

            if (c == '\"') {
                insideQuotes = !insideQuotes;
            }
            if (!insideQuotes){
                if (c == ':'){
                    afterPoints = true;
                }
                if (c == ',' || i == sJson.length()-1){
                    insertHashMap(key.toString(),value.toString(),hashMap);
                    afterPoints = false;
                    key.setLength(0);
                    value.setLength(0);
                }
            }
            if (insideQuotes && !afterPoints){
                state = State.READING_KEY;
            }
            if (afterPoints && !insideQuotes){
                if(isNumber(c)) {
                    state = State.READING_VALUE;
                    value.append(c);
                }
            }
            if(afterPoints && insideQuotes){
                state = State.READING_VALUE;
            }
            if (state.equals(State.READING_KEY)){
                if (isNumberOrChar(c) && insideQuotes){
                    key.append(c);
                }
            }

            if(state.equals(State.READING_VALUE)){

                if(isNumberOrChar(c) && insideQuotes) {
                    value.append(c);
                } else if (c == '{' && !insideQuotes) {
                    begin = i;
                    end = -1;
                    String subJson = sJson.substring(i);

                    sObjJson = sJson.substring(begin, i);
                    HashMap<String,Object> subHashMap = toObject(sObjJson);

                }
            }
        }
        return hashMap;
    }

    public static HashMap<String, List<List<Integer>>> getCharsPositions(String sJson) {
        List<List<Integer>> bracePositionsOpen = new ArrayList<>();
        List<List<Integer>> bracePositionsClose = new ArrayList<>();
        List<List<Integer>> brackPositionsOpen = new ArrayList<>();
        List<List<Integer>> brackPositionsClose = new ArrayList<>();
        int braceLevel = 0;
        int brackLevel = 0;

        for (int i = 0; i < sJson.length(); i++) {
            char c = sJson.charAt(i);

            switch (c) {
                case '{' -> {
                    braceLevel++;
                    while (bracePositionsOpen.size() <= braceLevel) {
                        bracePositionsOpen.add(new ArrayList<>());
                    }
                    bracePositionsOpen.get(braceLevel).add(i);
                }
                case '}' -> {
                    while (bracePositionsClose.size() <= braceLevel) {
                        bracePositionsClose.add(new ArrayList<>());
                    }
                    bracePositionsClose.get(braceLevel).add(i);
                    braceLevel--;
                }
                case '[' -> {
                    while (brackPositionsOpen.size() <= brackLevel) {
                        brackPositionsOpen.add(new ArrayList<>());
                    }
                    brackPositionsOpen.get(brackLevel).add(i);
                    brackLevel++;
                }
                case ']' -> {
                    while (brackPositionsClose.size() <= brackLevel) {
                        brackPositionsClose.add(new ArrayList<>());
                    }
                    brackPositionsClose.get(brackLevel).add(i);
                    brackLevel--;
                }
            }
        }
        HashMap<String,  List<List<Integer>>> hashMap = new HashMap<>();
        hashMap.put("{", bracePositionsOpen);
        hashMap.put("}", bracePositionsClose);
        hashMap.put("[", brackPositionsOpen);
        hashMap.put("]", brackPositionsClose);
        return hashMap;
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
