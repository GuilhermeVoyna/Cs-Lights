package org.example;


import org.example.csgsi.MapState;
import org.example.server.GSIServer;

import java.io.IOException;

import static org.example.csgsi.MapState.GameMode.COMPETITIVE;
import static org.example.csgsi.MapState.GamePhase.LIVE;

public class Main {
        public static void main(String[] args) throws IOException {

            GSIServer server = new GSIServer("/",8080);

            MapState map = new MapState(COMPETITIVE,"de_mirage",LIVE,3);
        }
}