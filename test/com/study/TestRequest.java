package com.study;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class TestRequest {
    private Request request;

    @Before
    public void before() {
        request = new Request();
    }

    @Test
    public void testRequest() {
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
        request.setHttpMethod(null);
    }

    @Test
    public void testEmptyHttpMethod(){
        request.setHttpMethod("");
    }

    @Test
    public void testNullUri(){
        request.setUri(null);
    }

    @Test
    public void testEmptyUri(){
        request.setUri("");
    }

    @Test
    public void testNullHeaders(){
        request.setHeaders(null);
    }

    @Test
    public void testEmptyHeaders(){
        Map<String, String> headers = new HashMap<>();
        request.setHeaders(headers);
    }
}
