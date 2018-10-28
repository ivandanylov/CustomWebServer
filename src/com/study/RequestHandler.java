package com.study;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Map;

public class RequestHandler {
    private BufferedReader reader;
    private BufferedWriter writer;
    private String webAppPath;

    public RequestHandler(BufferedReader reader, BufferedWriter writer, String webAppPath) {
        this.reader = reader;
        this.writer = writer;
        this.webAppPath = webAppPath;
    }

    public void handle() throws IOException {
        RequestParser requestParser = new RequestParser();
        Request request = requestParser.parseRequest(reader);

        System.out.println(request.getHttpMethod());
        System.out.println(request.getUri());
        for(Map.Entry entry  : request.getHeaders().entrySet()) {
            System.out.println("Key = '" + entry.getKey() + "'; Value = '" + entry.getValue() + "'");
        }
    }
}
