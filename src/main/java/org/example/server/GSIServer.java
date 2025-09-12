package org.example.server;

import com.sun.net.httpserver.HttpServer;
import org.example.context.ApplicationContext;
import org.example.csgsi.GameState;
import org.example.utils.json.JsonParser;

import java.lang.reflect.InvocationTargetException;
import java.util.logging.Logger;
import java.io.IOException;
import java.net.InetSocketAddress;

import static java.text.MessageFormat.format;


public class GSIServer {
    private static final Logger LOGGER = Logger.getLogger(GSIServer.class.getName());

    public GSIServer(String path, Integer port, ApplicationContext applicationContext) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        server.createContext(path, new GSIHandler(applicationContext));
        server.setExecutor(null);
        server.start();
        String info = format("Server started at port {0}", Integer.toString(server.getAddress().getPort()) );
        LOGGER.info(info);

    }
}
