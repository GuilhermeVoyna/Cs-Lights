package org.example;


import org.example.csgsi.GameState;
import org.example.csgsi.MapState;
import org.example.utils.json.JsonParser;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class Main {
        public static void main(String[] args) throws IOException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException {

            String sJson = """
{
  "map" : {
    "mode"  : 123    ,
    "test": "Alice",
    "D" : {
      "E" : {
        "F"  :  true
      },
      "G": {
        "H": true   ,
        "I": null
      }
    }
  },
  "J": "true"
}

""";
MapState mapState = new MapState(MapState.GameMode.COMPETITIVE,"dust", MapState.GamePhase.LIVE,1);
            GameState gameState = new GameState(mapState,null);
            gameState.setMap(mapState);
            System.out.println(sJson);
            var v = JsonParser.parseJson(sJson,gameState);
        }

}

