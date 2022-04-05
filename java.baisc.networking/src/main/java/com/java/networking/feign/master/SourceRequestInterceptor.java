package com.java.networking.feign.master;

import feign.RequestInterceptor;
import feign.RequestTemplate;

public class SourceRequestInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        requestTemplate.header("X-Source", "online-store-service");
    }
}
