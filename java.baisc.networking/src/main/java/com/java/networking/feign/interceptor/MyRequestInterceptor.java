package com.java.networking.feign.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;

public class MyRequestInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        requestTemplate.header("X-Source", "online-store-service");
    }
}
