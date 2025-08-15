package org.example;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class SimpleHttpServer {

    public static void main(String[] args) throws IOException {
        int port = 3000;
        String host = "127.0.0.1";

        HttpServer server = HttpServer.create(new InetSocketAddress(host, port), 0);
        server.createContext("/", new MyHandler());
        server.setExecutor(null); // uses default executor
        System.out.println("Listening at http://" + host + ":" + port);
        server.start();
    }

    static class MyHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String method = exchange.getRequestMethod();

            if ("POST".equalsIgnoreCase(method)) {
                System.out.println("Handling POST request...");

                InputStream is = exchange.getRequestBody();
                byte[] data = is.readAllBytes();
                String body = new String(data);
                System.out.println("POST payload: " + body);

                String response = "";
                exchange.sendResponseHeaders(200, response.length());
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            } else {
                System.out.println("Not expecting other request types...");

                String response = "<html><body>HTTP Server at http://127.0.0.1:3000</body></html>";
                exchange.getResponseHeaders().add("Content-Type", "text/html");
                exchange.sendResponseHeaders(200, response.length());
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            }
        }
    }
}
