package java_project;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DemoCsvOutputWriter {

    public static void main(String[] args) throws IOException, InterruptedException {
        ExecutorService worker = Executors.newFixedThreadPool(2);
        for (int index = 1; index < 1000; index++) {
            worker.execute(new CsvOutputThread(index));
        }
        worker.shutdown();

        // 休眠子线程，等待thread线程全部执行完毕
        Thread.sleep(5000);
        // 必须确保输出文件流被关闭
        CsvOutputStream.getInstance().close();
    }

    private static void testMultiThreadsFileStreams() throws InterruptedException, IOException {
        for (int index = 0; index < 2; index++) {
            new Thread(() -> {
                try {
                    CsvOutputStream.getInstance().writeLine();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }).start();
        }

        Thread.sleep(5000);
        CsvOutputStream.getInstance().close();
    }
}
