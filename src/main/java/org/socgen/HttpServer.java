package org.socgen;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.socgen.config.Configuration;
import org.socgen.config.ConfigurationManager;
import org.socgen.core.ServerListenerThread;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;


@SpringBootApplication
public class HttpServer
{
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpServer.class);

    public static void main( String[] args ) throws IOException {
        LOGGER.info("Server Started...");
        ConfigurationManager.getInstance().loadConfigurationFile("src/main/resources/http.json");
        Configuration conf = ConfigurationManager.getInstance().getCurrentConfiguration();

        LOGGER.info("Server started at port : " + conf.getPort());
        LOGGER.info("Server started at webRoot : " + conf.getWebroot());

        ServerListenerThread serverListenerThread = new ServerListenerThread(conf.getPort(), conf.getWebroot());
        serverListenerThread.start();

    }
}
