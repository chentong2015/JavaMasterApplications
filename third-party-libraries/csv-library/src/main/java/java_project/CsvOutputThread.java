package java_project;

import java.io.IOException;

public class CsvOutputThread implements Runnable {

    private int count;

    public CsvOutputThread(int count) {
        this.count = count;
    }

    @Override
    public void run() {
        System.out.println("Thread number: " + count);
        try {
            CsvOutputStream.getInstance().writeLongLine(count);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
