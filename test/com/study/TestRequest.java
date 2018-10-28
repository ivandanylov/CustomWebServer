package com.study;

import org.junit.Test;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class TestRequest {
    @Test
    public void testRequest() {
       Request request = new Request();
       request.setHttpMethod("GET");
       request.setUri("/index.html");

       Map<String, String> headers = new HashMap<>();
       headers.put("Host", "localhost:3000");
       request.setHeaders(headers);

       assertEquals("GET", request.getHttpMethod());
       assertEquals("/index.html", request.getUri());

       assertEquals(headers.size(), request.getHeaders().size());
       assertEquals(headers.get("Host"), request.getHeaders().get("Host"));
    }

    @Test(expected = RequestException.class)
    public void testNullHttpMethod(){
        Request request = new Request();
        request.setHttpMethod(null);
    }

    @Test(expected = RequestException.class)
    public void testEmptyHttpMethod(){
        Request request = new Request();
        request.setHttpMethod("");
    }

    @Test(expected = RequestException.class)
    public void testNullUri(){
        Request request = new Request();
        request.setUri(null);
    }

    @Test(expected = RequestException.class)
    public void testEmptyUri(){
        Request request = new Request();
        request.setUri("");
    }

    @Test(expected = RequestException.class)
    public void testNullHeaders(){
        Request request = new Request();
        request.setHeaders(null);
    }

    @Test(expected = RequestException.class)
    public void testEmptyHeaders(){
        Request request = new Request();
        Map<String, String> headers = new HashMap<>();
        request.setHeaders(headers);
    }

    @Test
    public void testRequestParser() throws IOException {
        RequestParser requestParser = new RequestParser();

        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
        writer.write("GET /index.html HTTP/1.1\n");
//Host: localhost:3000
//Connection: keep-alive
    }
}
