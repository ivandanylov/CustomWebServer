package com.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


//GET /index.html HTTP/1.1
//Host: localhost:3000
//Connection: keep-alive
//Cache-Control: max-age=0
//Upgrade-Insecure-Requests: 1
//User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.100 Safari/537.36
//Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8
//        Accept-Encoding: gzip, deflate, br
//        Accept-Language: en-US,en;q=0.9,ru;q=0.8
//        Cookie: Idea-d6f635c4=f2b187c1-7d88-41f6-a473-ac3348c0a1f6; JSESSIONID=274987ED35B0C069E766D0B96C724141


public class RequestParser {
    public Request parseRequest(BufferedReader reader) throws IOException {
        Request request = new Request();

        injectHeaders(request, reader);

        return request;
    }

    private void injectUriAndMethod(Request request, String requestLine) {
        String[] splittedFirstLine = requestLine.split("\\ ");

        if (splittedFirstLine == null || splittedFirstLine.length < 2) {
            ResponseWriter responseWriter = new ResponseWriter();
            responseWriter.sendBadRequestResponse();
        }

        request.setHttpMethod(splittedFirstLine[0]);
        request.setUri(splittedFirstLine[1]);
    }

    private void injectHeaders(Request request, BufferedReader reader) throws IOException {
        String requestLine = reader.readLine();
        injectUriAndMethod(request, requestLine);

        Map<String, String> headers = new HashMap<>();
        String headerLine;
        while ((headerLine = reader.readLine()) != null && !headerLine.isEmpty()) {
            String[] headerLines = headerLine.split("\\:\\ ");
            headers.put(headerLines[0], headerLines[1]);
        }

        if (!headers.isEmpty()) {
            request.setHeaders(headers);
        }
    }
}
