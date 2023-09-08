package com.java.others.CommonIO;

import feign.Response;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.SerializationUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

// Apache Commons IO library 公共IO类库
// Utility classes, stream implementations, file filters, file comparators, endian transformation classes

// TODO. SerializationUtils Class of Spring Framework 框架自带的序列化工具
public class ApacheCommonsIO {

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

    private static boolean testIOUtilsCopy(InputStream inputStream) throws Exception {
        StringWriter writer = new StringWriter();
        IOUtils.copy(inputStream, writer, Charset.defaultCharset());
        return writer.toString().contains("maintenance");
    }


    class User implements Serializable {

        private int id;
        private String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}