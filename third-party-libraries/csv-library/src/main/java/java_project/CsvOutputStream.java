package java_project;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

// TODO. 自定义"单列且线程安全"的CSV输出文件流
public final class CsvOutputStream {

    private FileOutputStream fileOutputStream;
    private BufferedOutputStream bufferedOutputStream;
    private final String lineSeparator = System.getProperty("line.separator");

    private CsvOutputStream() {
        try {
            fileOutputStream = new FileOutputStream("checkOutPut2.csv");
            bufferedOutputStream = new BufferedOutputStream(fileOutputStream);

            String headers = "message";
            bufferedOutputStream.write(headers.concat(lineSeparator).getBytes());
        } catch (IOException exception) {
            System.out.println("Init Failed");
        }
    }

    private static final class InstanceHolder {
        private static final CsvOutputStream instance = new CsvOutputStream();
    }

    public static CsvOutputStream getInstance() throws IOException {
        return InstanceHolder.instance;
    }

    // 1. 多个线程同时调用bufferedOutputStream插入，数据存储顺序没有保证
    // 2. 如果添加synchronized线程同步锁，保证数据的完整插入
    public synchronized void writeLine() throws IOException {
        // 3. 方法内部的局部变量不被线程共享，所以值的增长不会被扰乱
        for (int index = 0; index < 1000; index++) {
            String line = "line" + index + lineSeparator;
            bufferedOutputStream.write(line.getBytes());
        }
    }

    // 4. 局部变量在创建过程中不会受到多线程的影响，写入的数据是没有问题
    public void writeLongLine(int threadId) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        for (int index = 1; index < 100; index++) {
            stringBuilder.append("line " + index);
        }
        String line = threadId + stringBuilder.toString().concat(lineSeparator);
        bufferedOutputStream.write(line.getBytes());
    }

    // Flush缓存数据，关闭输出的文件流
    public void close() throws IOException {
        bufferedOutputStream.flush();
        bufferedOutputStream.close();
        fileOutputStream.close();
    }
}
