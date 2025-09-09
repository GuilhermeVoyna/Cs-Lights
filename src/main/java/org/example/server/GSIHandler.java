package org.example.server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.example.context.ApplicationContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.example.utils.json.JsonUtils;

public class GSIHandler  implements HttpHandler {

    ApplicationContext applicationContext;
    public GSIHandler(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }




    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String method = exchange.getRequestMethod();
        String response;

        switch (method.toUpperCase()) {

            case "POST":
                try (InputStream is = exchange.getRequestBody()) {
                    String sJson = JsonUtils.inputStreamToString(exchange.getRequestBody());
                    System.out.println(sJson);
                }

                response = "this is a post";
                exchange.sendResponseHeaders(200, response.length());
                try (OutputStream os = exchange.getResponseBody()) {
                    os.write(response.getBytes());
                }
            break;

            case "GET":
                String gameMode = applicationContext.getGameState().getMapState().getGameMode().toString();
                response = String.format("<html><body>Server is running...  %s </body></html>", gameMode);
                exchange.sendResponseHeaders(200, response.length());
                try (OutputStream os = exchange.getResponseBody()) {
                    os.write(response.getBytes());
                }
            break;

            default:
                response = "Method Not Allowed";
                exchange.sendResponseHeaders(405, response.length());
                try (OutputStream os = exchange.getResponseBody()) {
                    os.write(response.getBytes());
                }
            break;
        }
    }
}
