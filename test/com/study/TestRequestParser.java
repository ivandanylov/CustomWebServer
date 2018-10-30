package com.study;

import com.study.exceptions.BadRequestException;
import org.junit.Assert;
import org.junit.Test;

import java.io.*;

public class TestRequestParser {
    @Test
    public void testRequestParser() {
        String testInputString = "GET /index.html HTTP/1.1\n".concat("Host: localhost:3000\n");

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new ByteArrayInputStream(testInputString.getBytes())));

        RequestParser requestParser = new RequestParser();
        try {
            Request request = requestParser.parseRequest(reader);

            Assert.assertEquals("GET /index.html localhost:3000", request.getHttpMethod()
                    .concat(" ")
                    .concat(request.getUri())
                    .concat(" ")
                    .concat(request.getHeaders().get("Host"))
            );
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test(expected = BadRequestException.class)
    public void testBadHttpMethodUriRequest() throws BadRequestException {
        String testInputString = "HTTP/1.1\n".concat("Host: localhost:3000\n");

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new ByteArrayInputStream(testInputString.getBytes())));

        RequestParser requestParser = new RequestParser();
        requestParser.parseRequest(reader);
    }

    @Test(expected = BadRequestException.class)
    public void testBadHeadersRequest() throws BadRequestException {
        String testInputString = "GET /index.html HTTP/1.1\n";

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new ByteArrayInputStream(testInputString.getBytes())));

        RequestParser requestParser = new RequestParser();
        requestParser.parseRequest(reader);
    }
}
