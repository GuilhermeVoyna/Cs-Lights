package org.example;


import org.example.server.GSIServer;

import java.io.IOException;

public class Main {
        public static void main(String[] args) throws IOException {

            GSIServer server = new GSIServer("/",8080);
        }
}