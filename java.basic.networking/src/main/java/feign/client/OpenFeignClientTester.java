package feign.client;

import feign.*;
import feign.httpclient.ApacheHttpClient;
import feign.interceptor.AuthRequestInterceptor;
import feign.interceptor.CacheRequestInterceptor;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.retryer.MyNativeRetryer;

import java.util.List;
import java.util.concurrent.TimeUnit;

// TODO: https://github.com/OpenFeign/feign
// Feign allows you to write your own code on top of http libraries such as Apache HC
// 构建在Http上层的类库，封装在Http类库基础上

// 通过将注解处理成模板请求，来指定HTTP Request
// 1. 可以使用常见的http client来build
// 2. 可以自定义编码和解码所使用的类库，Java层面只处理对象，不考虑数据的解析
// 3. 直接通过调用方法来执行Http Client请求，减少对请求的自定义配置和封装
// 4. 提供日志配置
// 5. 提供Http Client请求的Timeout

// Use Feign with Spring WebFlux (SSE)
// https://github.com/playtika/feign-reactive
public class OpenFeignClientTester {

    public static void main(String[] args) {
        Target<NullResponseFeignClient> target = new Target.HardCodedTarget<>(NullResponseFeignClient.class, "http://localhost:8080/");
        NullResponseFeignClient client = Feign.builder()
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder()) // 必须添加解码器来解析返回的json数据
                .target(target);
        List<String> result = client.getInformation();
        if (result == null) {
            System.out.println("response null"); // 请求的结果有可能返回为null
        } else {
            System.out.println("result ok");
        }
    }

    public void testFeignBuilder() {
        FeignRequestClient feignClient = Feign.builder()
                .client(new ApacheHttpClient())  // 设置选择的Http client
                .encoder(new JacksonEncoder())   // 编码和解码器的选择
                .decoder(new JacksonDecoder())
                .retryer(new MyNativeRetryer())  // 添加retryer重连器
                .requestInterceptor(new AuthRequestInterceptor()) // 添加拦截器设置
                .requestInterceptor(new CacheRequestInterceptor())// 添加多个拦截器
                .target(FeignRequestClient.class, "https://localhost/demo"); // 设置target目标的默认url网络路径
        feignClient.callChaosFast();

        // 使用Target构建，但本质上还是通过Feign.builder()来target
        Target<FeignRequestClient> clientTarget = new Target.HardCodedTarget<>(FeignRequestClient.class, "https://localhost/");
        FeignRequestClient client = Feign.builder().target(clientTarget);
        client.callChaosFast();
    }

    // 使用Option来配置Feign请求的timeout参数
    public static void testFeignClientTimeout() {
        Feign.Builder builder = Feign.builder();
        builder.options(new Request.Options(
                100, TimeUnit.SECONDS,
                100, TimeUnit.SECONDS,
                true));
    }

    // 使用默认的feign client retryer: 重试间隔时间，重试最大周期，最大尝试次数
    public static void testFeignDefaultRetryer() {
        Feign.Builder builder = Feign.builder();
        Retryer retryer = new Retryer.Default(100L, TimeUnit.SECONDS.toSeconds(3L), 5);
        builder.retryer(retryer);
    }

    // TODO. 构建异步的Feign Client
    public void testAsyncFeignClient() {
        FeignRequestClient feignRequestClient = AsyncFeign.asyncBuilder()
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .requestInterceptor(new AuthRequestInterceptor())
                .target(FeignRequestClient.class, "http://localhost:8082");
        feignRequestClient.callChaosFast();
    }
}
