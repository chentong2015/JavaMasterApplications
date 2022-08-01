package jax.restful.service;

import jakarta.ws.rs.client.ClientRequestContext;
import jakarta.ws.rs.client.ClientRequestFilter;

import java.io.IOException;

public class MyClientRequestFilter implements ClientRequestFilter {

    // 对客户端的请求进行过滤
    @Override
    public void filter(ClientRequestContext clientRequestContext) throws IOException {
        String token = (String) clientRequestContext.getHeaders().getFirst("Authorization");
        if (token != null) {
            System.out.println("valid");
        }
    }
}
