package org.example;


import org.example.utils.json.JsonObject;

import java.io.IOException;

public class Main {
        public static void main(String[] args) throws IOException {

            String sJson = """
{
  "A" : {
    "B"  : 123    ,
    "C": "Alice",
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

            System.out.println(sJson);
            JsonObject.toObject(sJson);
        }

}