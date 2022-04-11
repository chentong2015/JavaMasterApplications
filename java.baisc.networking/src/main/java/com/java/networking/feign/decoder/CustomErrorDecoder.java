package com.java.networking.feign.decoder;

import feign.FeignException;
import feign.Response;
import feign.RetryableException;
import feign.codec.ErrorDecoder;

public class CustomErrorDecoder implements ErrorDecoder {

    // TODO. 自定义实现Feign client retry on exception
    // 根据请求return status code, 返回指定的Retryable异常, 用于retryer的处理逻辑
    @Override
    public Exception decode(String methodKey, Response response) {
        FeignException exception = feign.FeignException.errorStatus(methodKey, response);
        // 可以从response中获取到到请求的URL
        String url = response.request().url();
        int status = response.status();
        if (status >= 500) {
            return new RetryableException(
                    response.status(),
                    exception.getMessage(),
                    response.request().httpMethod(),
                    exception,
                    null,
                    response.request());
        }
        return exception;
    }
}
