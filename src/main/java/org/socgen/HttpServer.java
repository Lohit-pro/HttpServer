package org.socgen;

import org.socgen.config.Configuration;
import org.socgen.config.ConfigurationManager;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HttpServer
{
    public static void main( String[] args ) {
        System.out.println("Starting server...");
        ConfigurationManager.getInstance().loadConfigurationFile("src/main/resources/http.json");
        Configuration conf = ConfigurationManager.getInstance().getCurrentConfiguration();

        System.out.println("Server started at port : " + conf.getPort());
        System.out.println("Server started at webRoot : " + conf.getWebroot());

    }
}
