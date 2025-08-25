package org.example;


import org.example.context.ApplicationContext;
import org.example.csgsi.GameState;
import org.example.csgsi.MapState;
import org.example.server.GSIServer;
import org.example.utils.json.JsonObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

import static org.example.csgsi.MapState.GameMode;
import static org.example.csgsi.MapState.GamePhase;

public class Main {
        public static void main(String[] args) throws IOException {

            String sJson = """
{
  "user": {
    "id": 123,
    "name": "Alice",
    "settings": {
      "theme": {"dark"},
      "notifications": {
        "email": true,
        "sms": false
      }
    }
  },
  "active": true
}
""";
            HashMap<String, List<List<Integer>>> hs = new HashMap<>();
            System.out.println(sJson);
            hs = JsonObject.getCharsPositions(sJson);
            System.out.println(hs);
        }

}