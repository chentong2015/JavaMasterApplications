package com.java.networking.feign;

import com.java.networking.feign.client.FeignRequestClient;
import com.java.networking.feign.interceptor.MyRequestInterceptor;
import feign.AsyncFeign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;

public class BaseAsyncFeignClient {

    // 构建异步的Feign Client
    public void testAsyncFeignClient() {
        FeignRequestClient feignRequestClient = AsyncFeign.asyncBuilder()
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .requestInterceptor(new MyRequestInterceptor())
                .target(FeignRequestClient.class, "http://localhost:8082");
        feignRequestClient.callChaosFast();
    }
}
