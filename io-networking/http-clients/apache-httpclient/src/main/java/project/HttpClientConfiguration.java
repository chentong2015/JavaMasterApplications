package project;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.ssl.SSLContextBuilder;

import javax.net.ssl.SSLContext;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;

public abstract class HttpClientConfiguration {

    protected final ClientHttpRequestFactory createRequestFactory() throws Exception {
        HttpComponentsClientHttpRequestFactory requestFactory =
                new HttpComponentsClientHttpRequestFactory(httpClient());

        requestFactory.setReadTimeout(100);
        requestFactory.setConnectTimeout(100);
        return requestFactory;
    }

    protected final HttpClient httpClient() throws Exception {
        HttpClientBuilder builder = HttpClientBuilder.create()
                .setSSLSocketFactory(this.connectionSocketFactory());
        this.configureHttpClient(builder);
        return builder.build();
    }

    protected void configureHttpClient(HttpClientBuilder builder) {
        builder.addInterceptorFirst(new RemoveSoapHeadersInterceptor());
    }

    protected SSLConnectionSocketFactory connectionSocketFactory() throws Exception {
        return new SSLConnectionSocketFactory(this.sslContext());
    }

    protected SSLContext sslContext() throws Exception {
        SSLContextBuilder builder = SSLContextBuilder.create();
        // To do ..
        return builder.build();
    }
}
