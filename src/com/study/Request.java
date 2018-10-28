package com.study;

import java.util.Map;

public class Request {
    private String uri;
    private String httpMethod;
    private Map<String, String> headers;

    public Request() {
    }

    public void setUri(String uri) {
        if (uri == null || uri.isEmpty()) {
            throw new RequestException("Property 'uri' is empty!");
        }

        this.uri = uri;
    }

    public void setHttpMethod(String httpMethod) {
        if (httpMethod == null || httpMethod.isEmpty()) {
            throw new RequestException("Property 'httpMethod' is empty!");
        }

        this.httpMethod = httpMethod;
    }

    public void setHeaders(Map<String, String> headers) {
        if (headers == null || headers.isEmpty()) {
            throw new RequestException("Property 'headers' is empty!");
        }

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
