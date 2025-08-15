package org.example.server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

public class GSIHandler  implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String method = exchange.getRequestMethod();
        String response;

        if("POST".equalsIgnoreCase(method))
        {
            response = "this is a post";
            exchange.sendResponseHeaders(200,response.length());
            try (OutputStream os = exchange.getResponseBody()){
                os.write(response.getBytes());
            }
        }
        response = "method not allowed";
        exchange.sendResponseHeaders(400,response.length());
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(response.getBytes());
        }
    }
}
