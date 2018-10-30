package com.study;

import com.study.exceptions.BadRequestException;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RequestParser {
    public Request parseRequest(BufferedReader reader) throws BadRequestException {
        Request request = new Request();

        injectHeaders(request, reader);

        return request;
    }

    private void injectUriAndMethod(Request request, String requestLine) throws BadRequestException{
        String[] splittedFirstLine = requestLine.split("\\ ");

        if (splittedFirstLine == null || splittedFirstLine.length < 2) {
            throw new BadRequestException("Exception on retrieving of http method or uri)");
        }

        request.setHttpMethod(splittedFirstLine[0]);
        request.setUri(splittedFirstLine[1]);
    }

    private void injectHeaders(Request request, BufferedReader reader) throws BadRequestException {
        Map<String, String> headers = new HashMap<>();

        try {
            String requestLine = reader.readLine();
            injectUriAndMethod(request, requestLine);

            String headerLine;
            while ((headerLine = reader.readLine()) != null && !headerLine.isEmpty()) {
                String[] headerLines = headerLine.split("\\:\\ ");
                headers.put(headerLines[0], headerLines[1]);
            }
        } catch (IOException e) {
            throw new BadRequestException("IOException on parsing request: ");
        }

        if (!headers.isEmpty()) {
            request.setHeaders(headers);
        } else {
            throw new BadRequestException("Exception on retrieving of headers");
        }
    }
}
