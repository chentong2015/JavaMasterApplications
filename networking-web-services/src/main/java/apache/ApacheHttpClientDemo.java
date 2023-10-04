package apache;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ApacheHttpClientDemo {

    // 使用Apache HttpClientBuilder来构建自定义的Http Client
    public void testHttpClientBuilder() {
        HttpClientBuilder httpClientBuilder = HttpClientBuilder
                .create()
                .setMaxConnPerRoute(50)
                .setMaxConnTotal(100);
        httpClientBuilder.setDefaultCookieStore(new BasicCookieStore());
        httpClientBuilder.disableAuthCaching();
    }

    // 请求的HttpClient网络流需要关闭，推荐使用try-with-resources
    public void testHttpClientsSync() throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet request = new HttpGet("http://example.org");
        request.addHeader("User-Agent", "Chrome");

        CloseableHttpResponse response = httpClient.execute(request);
        int statusCode = response.getStatusLine().getStatusCode();

        // .getContent() 拿到所有的返回结果，其中包含 json result
        InputStream inputStream = response.getEntity().getContent();
        String result = String.valueOf(inputStream);

        // 追行读取网络返回的Stream流
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            System.out.println(line);
        }
    }
}
