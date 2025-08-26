package org.example;


import org.example.utils.json.JsonParameter;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class Main {
        public static void main(String[] args) throws IOException {

            String sJson = """
{
  "user": {
    "id": "123",
    "name": "Alice",
    "settings": {
      "theme": {
        "sempre": "true"
      },
      "notifications": {
        "email": "true",
        "sms": "false"
      }
    }
  },
  "active": "true"
}

""";

            System.out.println(sJson);
            JsonParameter.toObject(sJson);
        }

}