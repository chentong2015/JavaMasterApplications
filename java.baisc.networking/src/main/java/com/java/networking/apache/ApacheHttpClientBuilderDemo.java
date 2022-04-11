package com.java.networking.apache;

import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.HttpClientBuilder;

public class ApacheHttpClientBuilderDemo {

    // 使用Apache HttpClientBuilder来构建自定义的Http Client
    public void testHttpClientBuilder() {
        HttpClientBuilder httpClientBuilder = HttpClientBuilder
                .create()
                .setMaxConnPerRoute(50)
                .setMaxConnTotal(100);
        httpClientBuilder.setDefaultCookieStore(new BasicCookieStore());
        httpClientBuilder.disableAuthCaching();
    }
}
