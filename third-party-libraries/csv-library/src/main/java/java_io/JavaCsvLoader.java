package java_io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Stream;

public class JavaCsvLoader {

    public static void main(String[] args) throws IOException {
        String resourcePath = "sample.csv";
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream inputStream = classloader.getResourceAsStream(resourcePath);

        assert inputStream != null;
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {
            // 跳过CSV第一行的title
            Stream<String> streams = bufferedReader.lines().skip(1);

            // 将Stream<>流转成List<String>并处理
            List<String> lines = streams.toList();
            System.out.println(lines.get(0));
            System.out.println(lines.get(1));
        }
    }
}
