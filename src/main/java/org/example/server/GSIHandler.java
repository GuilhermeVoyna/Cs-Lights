package org.example.server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.example.context.ApplicationContext;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

import org.example.csgsi.GameState;
import org.example.utils.json.JsonObject;
import org.example.utils.json.JsonParser;
import org.example.utils.json.JsonUtils;

public class GSIHandler  implements HttpHandler {

    ApplicationContext applicationContext;
    public GSIHandler(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String method = exchange.getRequestMethod();
        String response;

        switch (method.toUpperCase()) {

            case "POST":

                try {
                    processJson(exchange);
                } catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException |
                         InstantiationException e) {
                    throw new RuntimeException(e);
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

    private void processJson(HttpExchange exchange)
            throws IOException, InvocationTargetException, IllegalAccessException,
            NoSuchMethodException, InstantiationException {

        if(applicationContext.isAttemptBlocked(exchange)){
            String response = "Retry-After "+ ApplicationContext.BLOCKED_TIME/1000 +" seconds";
            exchange.sendResponseHeaders(429, response.length());
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(response.getBytes());
            }
        }
        String requestBody = JsonUtils.inputStreamToString(exchange.getRequestBody());

        GameState currentGameState = getApplicationContext().getGameState();

        long startTime = System.nanoTime();

        HashMap<String, JsonObject> jsonFields = JsonParser.getFields(requestBody);

        String token = getJsonTokenValue(jsonFields);

        System.out.println("Token recebido: " + token);


        if (token.equals(applicationContext.getToken())) {
            GameState newGameState = JsonParser.parseJson(jsonFields, currentGameState);

            long endTime = System.nanoTime();
            double durationInMillis = (endTime - startTime) / 1_000_000.0;

            System.out.printf("JsonParser.parseJson demorou: %.3f ms%n", durationInMillis);
            String response = "OK";

            exchange.sendResponseHeaders(200, response.length());
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(response.getBytes());
            }
        } else {
            applicationContext.registerFailedAttempt(exchange);
            String response = "Wrong token";
            exchange.sendResponseHeaders(401, response.length());
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(response.getBytes());
            }
        }
    }
    private String getJsonTokenValue(HashMap<String, JsonObject> fields) {
        JsonObject field = fields.get("root.auth.token");
        return field != null && field.getValue() != null
                ? field.getValue().toString()
                : "";
    }
}
