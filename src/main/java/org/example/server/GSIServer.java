package org.example.server;

import com.sun.net.httpserver.HttpServer;

import java.text.MessageFormat;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.io.IOException;
import java.net.InetSocketAddress;

import static java.text.MessageFormat.*;

public class GSIServer {
    private static final Logger LOGGER = Logger.getLogger(GSIServer.class.getName());
    private final HttpServer server;

    public GSIServer(String path, Integer port) throws IOException {
        this.server=HttpServer.create(new InetSocketAddress(port), 0);
        server.createContext(path, new GSIHandler());
        server.setExecutor(null);
        this.server.start();
        String info = format("Server started at port {0}", Integer.toString(this.server.getAddress().getPort()) );
        LOGGER.info(info);
    }
}
