package org.socgen.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class HttpConnectionWorkerThread extends Thread{
    private Logger LOGGER = LoggerFactory.getLogger(HttpConnectionWorkerThread.class);
    private Socket socket;

    public HttpConnectionWorkerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();

            String html = "<html><head><title>HttpServer</title></head><body><h2>Hey this webpage is served by my http socket code</h2></body></html>";
            final String CRLF = "\n\r";

            String response = "HTTP/1.1 200 OK" + CRLF +
                    "Content-Length: " + html.getBytes().length + CRLF +
                    CRLF +
                    html +
                    CRLF + CRLF;

            outputStream.write(response.getBytes());
            socket.close();
//            serverSocket.close();
            inputStream.close();
            outputStream.close();

            try {
                sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            LOGGER.info("Connection processed successfully!");
        } catch (IOException e) {
            throw new RuntimeException(e);
            // TODO Handle later
        }
    }
}
