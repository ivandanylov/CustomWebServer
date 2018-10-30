package com.study;

import java.util.Map;

public class Request {
    private String uri;
    private String httpMethod;
    private Map<String, String> headers;

    public Request() {
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public String getUri() {
        return uri;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }
}
