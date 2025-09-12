package org.example;


import org.example.context.ApplicationContext;
import org.example.csgsi.GameState;
import org.example.csgsi.MapState;
import org.example.server.GSIServer;
import org.example.utils.json.JsonParser;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class Main {
        public static void main(String[] args) throws IOException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException {
                ApplicationContext applicationContext = new ApplicationContext("CSGSI");
                GSIServer gsi = new GSIServer("/",8080,applicationContext);
        }

}

