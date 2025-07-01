package org.socgen.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerListenerThread extends Thread{

    private static final Logger LOGGER = LoggerFactory.getLogger(ServerListenerThread.class);

    private int port;
    private String webroot;
    private ServerSocket serverSocket;

    public ServerListenerThread(int port, String webroot) throws IOException {
        this.port = port;
        this.webroot = webroot;
        this.serverSocket = new ServerSocket(this.port);
    }

    @Override
    public void run() {
        while (true){
            try {
                Socket socket = serverSocket.accept();

                LOGGER.info("Connection accepted : " + socket.getPort());

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
                socket.close();
//                serverSocket.close();
                inputStream.close();
                outputStream.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
