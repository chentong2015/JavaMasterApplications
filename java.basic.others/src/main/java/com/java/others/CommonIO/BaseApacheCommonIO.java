package com.java.others.CommonIO;

import base.model.User;
import feign.Response;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.SerializationUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

// Apache Commons IO library 公共IO类库
// Utility classes, stream implementations, file filters, file comparators, endian transformation classes

// TODO. SerializationUtils Class of Spring Framework 框架自带的序列化工具
public class BaseApacheCommonIO {

    // SerializationUtils: 对象的序列化和反序列化
    public void testSerializationUtils() {
        User user = new User(1, "test");
        byte[] data = SerializationUtils.serialize(user);
        User deserializedUser = SerializationUtils.deserialize(data);
        System.out.println(deserializedUser);
    }

    // IOUtils: 将指定的input stream流按照指定的编码格式转换成字符串 !!
    private String extractResponseBody(String method, Response response) {
        if (response.body() != null) {
            try {
                return IOUtils.toString(response.body().asInputStream(), StandardCharsets.UTF_8);
            } catch (IOException exception) {
                // Unable to read response body from server response
            }
        }
        return String.format("status %s invoking %s", response.status(), method);
    }
}
