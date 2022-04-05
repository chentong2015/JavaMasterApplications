package com.java.networking.feign;

import feign.RequestInterceptor;
import feign.RequestTemplate;

public class BaseRequestInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        requestTemplate.header("X-Source", "online-store-service");
    }
}
