package com.java.networking.feign.target.client;

import feign.RequestLine;

import java.net.URI;

public interface AbsolutePathClient {

    // URI parameter will be the host
    @RequestLine("GET /v1/sample/index")
    String index(URI baseUri);
}
