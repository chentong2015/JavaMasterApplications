package com.java.networking.jaxrs;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.sse.InboundSseEvent;
import jakarta.ws.rs.sse.SseEventSource;

import java.util.concurrent.TimeUnit;

// TODO. JAX-RS: javax restful web service
//       提供后端API的实现, 同时包含Client API
// https://gayerie.dev/epsi-poe-201703/javaee/11_jaxrs.html
// https://github.com/jakartaee/rest  jakarta.ws.rs

// TODO. JAX-RS bug的地方 ?
// JAX-RS Spec mandates that when the connection is lost, the reconnect MUST occur.
// But it is not possible to recognize when the connection is lost and when closed.
// This leads the JAX-RS client to re-connect whenever the server completes sending events !!
public class BaseJaxRs {

    // Jax.Rs Client 发送请求，获取到指定类型的返回数据
    public void testJaxRsClient() {
        Client client = ClientBuilder.newClient();
        WebTarget endpoint = client.target("http://www.server.net/person");
        // Person person = target.request().get(Person.class);

        long reconnectMillis = 100;
        SseEventSource sseEventSource = SseEventSource
                .target(endpoint)
                .reconnectingEvery(reconnectMillis, TimeUnit.MILLISECONDS).build();
        // 使用sseEventSource来注册，在error发生和完成的时候执行相应的逻辑 !!
        // sseEventSource.register(inboundSseEvent -> handleMessage(inboundSseEvent, onEvent));
        // sseEventSource.register(inboundSseEvent -> handleMessage(inboundSseEvent, onEvent), onError);
        // sseEventSource.register(inboundSseEvent -> handleMessage(inboundSseEvent, onEvent), onError, onComplete);
    }

    public void handleMessage(InboundSseEvent inboundSseEvent) {
        String data = inboundSseEvent.readData();
    }
}
