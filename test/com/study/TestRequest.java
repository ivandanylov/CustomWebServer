package com.study;

import org.junit.Test;

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

    @Test
    public void testNullHttpMethod(){
        Request request = new Request();
        request.setHttpMethod(null);
    }

    @Test
    public void testEmptyHttpMethod(){
        Request request = new Request();
        request.setHttpMethod("");
    }

    @Test
    public void testNullUri(){
        Request request = new Request();
        request.setUri(null);
    }

    @Test
    public void testEmptyUri(){
        Request request = new Request();
        request.setUri("");
    }

    @Test
    public void testNullHeaders(){
        Request request = new Request();
        request.setHeaders(null);
    }

    @Test
    public void testEmptyHeaders(){
        Request request = new Request();
        Map<String, String> headers = new HashMap<>();
        request.setHeaders(headers);
    }
}
