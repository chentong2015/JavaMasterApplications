package com.java.networking.jaxrs;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.ClientRequestContext;
import jakarta.ws.rs.client.WebTarget;

public class BaseJaxRs {

    private ClientRequestContext requestContext;

    // Jax.Rs Client 发送请求，获取到指定类型的返回数据
    public void testJaxRsClient() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://www.server.net/person");
        // Person person = target.request().get(Person.class);

        // 可以从请求的Context中获取请求的相关信息
        requestContext.getUri();
        requestContext.getHeaders();
    }
}
