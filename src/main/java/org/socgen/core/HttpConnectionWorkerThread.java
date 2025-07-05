package org.socgen.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class HttpConnectionWorkerThread extends Thread{
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpConnectionWorkerThread.class);
    private Socket socket;

    public HttpConnectionWorkerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        InputStream inputStream = null;
        OutputStream outputStream = null;

        try {
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();

            int _byte;

            while ((_byte = inputStream.read()) != -1) {
                System.out.print((char)_byte);
            }

            String html = "<html><head><title>HttpServer</title></head><body><h2>Hey this webpage is served by my http socket code</h2></body></html>";
            final String CRLF = "\r\n";

            String response = "HTTP/1.1 200 OK" + CRLF +
                    "Content-Length: " + html.getBytes().length + CRLF +
                    CRLF +
                    html +
                    CRLF + CRLF;

            outputStream.write(response.getBytes());

            LOGGER.info("Connection processed successfully!");

            try {
                sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        } catch (IOException e) {
            LOGGER.error("Exception Occurred.", e);
            throw new RuntimeException(e);
        } finally {
            try {
                if (socket != null)
                    socket.close();

                if (inputStream != null)
                    inputStream.close();

                if (outputStream != null)
                    outputStream.close();

            } catch (IOException e) {
                LOGGER.debug("Error at closing socket");
            }
        }
    }
}
