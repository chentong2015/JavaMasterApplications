package com.java.networking.jaxrs;

import jakarta.ws.rs.client.ClientRequestContext;

import java.net.URI;
import java.util.List;
import java.util.Map;

// 可以从请求的Context中获取请求的相关信息
public class MyClientRequestContext {

    private final ClientRequestContext requestContext;

    public MyClientRequestContext(ClientRequestContext requestContext) {
        this.requestContext = requestContext;
    }

    public URI getUri() {
        return requestContext.getUri();
    }

    public Map<String, List<Object>> getHeaders() {
        return requestContext.getHeaders();
    }
}
