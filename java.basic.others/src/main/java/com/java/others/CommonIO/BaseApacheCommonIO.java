package com.java.others.CommonIO;

import feign.Response;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

// Apache Commons IO library 公共IO类库
// Utility classes, stream implementations, file filters, file comparators, endian transformation classes
public class BaseApacheCommonIO {

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
