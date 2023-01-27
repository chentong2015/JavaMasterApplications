package com.java.others.csv;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JavaCsvLoader {

    // TODO. 通过Stream<String>来跳过CSV第一行的title，然后使用Stream流转成List<String>
    public static void main(String[] args) throws IOException {
        String resourcePath = "sample.csv";
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream inputStream = classloader.getResourceAsStream(resourcePath);

        assert inputStream != null;
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {
            Stream<String> streams = bufferedReader.lines().skip(1);
            List<String> lines = streams.collect(Collectors.toList());
            System.out.println(lines.get(0));
            System.out.println(lines.get(1));
        }
    }
}
