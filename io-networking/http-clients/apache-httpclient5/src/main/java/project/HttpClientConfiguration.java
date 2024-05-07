package project;

import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.config.ConnectionConfig;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManagerBuilder;
import org.apache.hc.client5.http.ssl.SSLConnectionSocketFactory;
import org.apache.hc.client5.http.ssl.SSLConnectionSocketFactoryBuilder;
import org.apache.hc.core5.http.io.SocketConfig;
import org.apache.hc.core5.http.ssl.TLS;
import org.apache.hc.core5.pool.PoolConcurrencyPolicy;
import org.apache.hc.core5.pool.PoolReusePolicy;
import org.apache.hc.core5.ssl.SSLContextBuilder;

import org.apache.hc.core5.ssl.SSLContexts;
import org.apache.hc.core5.util.TimeValue;
import org.apache.hc.core5.util.Timeout;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;

import javax.net.ssl.SSLContext;

// TODO. Spring-web v5 -> v6 无法设置setReadTimeout()
//  HttpClient v4 -> v5 无法再设置.setSSLSocketFactory(), 通过ConnectionManager来配置
// https://hc.apache.org/httpcomponents-client-5.3.x/migration-guide/migration-to-classic.html
public abstract class HttpClientConfiguration {

    RequestConfig requestConfig;

    protected final ClientHttpRequestFactory createRequestFactory() throws Exception {
        HttpComponentsClientHttpRequestFactory requestFactory =
                new HttpComponentsClientHttpRequestFactory(this.httpClient());

        // 这里的ReadTimeout实际被设置成RequestConfig的SocketTimeout
        // requestFactory.setReadTimeout(100);
        requestFactory.setConnectTimeout(100);
        return requestFactory;
    }

    protected final HttpClient httpClient() throws Exception {
        HttpClientBuilder builder = HttpClientBuilder.create()
                .setConnectionManager(getConnectionManager());

        this.configureHttpClient(builder);
        return builder.build();
    }

    private PoolingHttpClientConnectionManager getConnectionManager() {
        return PoolingHttpClientConnectionManagerBuilder.create()
                .setSSLSocketFactory(SSLConnectionSocketFactoryBuilder.create()
                        .setSslContext(SSLContexts.createSystemDefault())
                        .setTlsVersions(TLS.V_1_3)
                        .build())
                .setDefaultSocketConfig(SocketConfig.custom()
                        .setSoTimeout(Timeout.ofMinutes(1))
                        .build())
                .setPoolConcurrencyPolicy(PoolConcurrencyPolicy.STRICT)
                .setConnPoolPolicy(PoolReusePolicy.LIFO)
                .setDefaultConnectionConfig(ConnectionConfig.custom()
                        .setSocketTimeout(Timeout.ofMinutes(1))
                        .setConnectTimeout(Timeout.ofMinutes(1))
                        .setTimeToLive(TimeValue.ofMinutes(10))
                        .build())
                .build();
    }

    // 交给继承类进行额外的HttpClient的配置
    protected void configureHttpClient(HttpClientBuilder builder) {
        builder.addRequestInterceptorFirst(new RemoveSoapHeadersInterceptor());
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
