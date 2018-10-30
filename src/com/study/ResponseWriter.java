package com.study;

import java.io.BufferedWriter;
import java.io.IOException;

public class ResponseWriter {
    private BufferedWriter socketWriter;

    public ResponseWriter(BufferedWriter socketWriter) {
        this.socketWriter = socketWriter;
    }

    public void sendSuccessResponse(String content) throws IOException {
        socketWriter.write(content);
        socketWriter.flush();
    }

    public void sendNotFoundResponse() throws IOException {
        socketWriter.write("HTTP/1.1 404 NOT OK\n");
        socketWriter.write("\n");

        socketWriter.write("Error 404");

        socketWriter.flush();
    }

    public void sendBadRequestResponse() throws IOException {
        socketWriter.write("HTTP/1.1 400 NOT OK\n");
        socketWriter.write("\n");

        socketWriter.write("Error 400");

        socketWriter.flush();
    }

    public void sendInternalServerErrorResponse() throws IOException {
        socketWriter.write("HTTP/1.1 500 NOT OK\n");
        socketWriter.write("\n");

        socketWriter.write("Error 500");

        socketWriter.flush();
    }
}
