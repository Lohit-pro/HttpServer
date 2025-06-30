package org.socgen;

import org.socgen.config.Configuration;
import org.socgen.config.ConfigurationManager;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

@SpringBootApplication
public class HttpServer
{
    public static void main( String[] args ) {
        System.out.println("Starting server...");
        ConfigurationManager.getInstance().loadConfigurationFile("src/main/resources/http.json");
        Configuration conf = ConfigurationManager.getInstance().getCurrentConfiguration();

        System.out.println("Server started at port : " + conf.getPort());
        System.out.println("Server started at webRoot : " + conf.getWebroot());

        try {
            ServerSocket serverSocket = new ServerSocket(conf.getPort());
            Socket socket = serverSocket.accept();

            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();

            String html = "<html><head><title>NTNMK</title></head><body><h2>Hey this webpage is served by my http socket code</h2></body></html>";
            final String CRLF = "\n\r";

            String response = "HTTP/1.1 200 OK" + CRLF +
                    "Content-Length: " + html.getBytes().length + CRLF +
                    CRLF +
                    html +
                    CRLF + CRLF;

            outputStream.write(response.getBytes());

            serverSocket.close();
            socket.close();
            inputStream.close();
            outputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
