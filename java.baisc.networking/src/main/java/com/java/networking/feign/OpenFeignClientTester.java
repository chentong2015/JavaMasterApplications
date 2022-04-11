package com.java.networking.feign;

import com.java.networking.feign.client.FeignRequestClient;
import com.java.networking.feign.interceptor.AuthRequestInterceptor;
import com.java.networking.feign.interceptor.CacheRequestInterceptor;
import com.java.networking.feign.retryer.MyNativeRetryer;
import feign.Feign;
import feign.Request;
import feign.Retryer;
import feign.Target;
import feign.httpclient.ApacheHttpClient;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;

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
public class OpenFeignClientTester {

    // 基本使用方式:
    // 1. 定义接口，通过注解来配置请求的模板
    // 2. 配置请求的参数和传递的数据(格式)
    // 3. 自定义接口中的default方法
    // 4. target()到指定的网址，通过调用接口方法来执行不同的Http请求

    public static void main(String[] args) {
        FeignRequestClient feignClient = Feign.builder()
                .client(new ApacheHttpClient())  // 设置选择的Http client
                .encoder(new JacksonEncoder())   // 编码和解码器的选择
                .decoder(new JacksonDecoder())
                .retryer(new MyNativeRetryer())  // 添加retryer重连器
                .requestInterceptor(new AuthRequestInterceptor()) // 添加拦截器设置
                .requestInterceptor(new CacheRequestInterceptor())// 添加多个拦截器
                .target(FeignRequestClient.class, "https://localhost/demo");
        feignClient.callChaosFast();

        // 使用Target构建，但本质上还是通过Feign.builder()来target
        Target<FeignRequestClient> clientTarget = new Target.HardCodedTarget<>(
                FeignRequestClient.class, "https://localhost/");
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
}
