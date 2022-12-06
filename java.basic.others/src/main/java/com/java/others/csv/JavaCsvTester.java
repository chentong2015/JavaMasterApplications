package com.java.others.csv;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class JavaCsvTester {

    // TODO. .csv格式的文件使用""**""双双引号来包含字符串中的双引号
    public static void main(String[] args) {
        String lineSeparator = System.getProperty("line.separator");
        String fileName = "output.csv";
        try (FileOutputStream out = new FileOutputStream(fileName);
             BufferedOutputStream stream = new BufferedOutputStream(out)) {

            StringBuilder warningBuilder = new StringBuilder();
            warningBuilder.append("\"\"\"").append("first item ").append("\"\"\"").append(",")
                    .append("\"second item\"").append(",")
                    .append("end item").append(lineSeparator);
            stream.write(warningBuilder.toString().getBytes(StandardCharsets.UTF_8));

            // Flush内存中缓存的字节，写入底层的输出
            // Flushes this buffered output stream.
            // Forces any buffered output bytes to be written out to the underlying output stream
            stream.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
