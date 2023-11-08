package io_network.aio.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.StandardSocketOptions;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BaseAioClient {

    private static CountDownLatch serverStatus = new CountDownLatch(1);

    public static void main(String[] args) throws Exception {
        try {
            // �̳߳��е�ÿ���̶߳��ڵȴ�IO�¼�����IO�¼����֮�󣬵��ó��е��̴߳���connectionHandler
            ExecutorService threadPool = Executors.newFixedThreadPool(10);
            AsynchronousChannelGroup asyncChannelGroup = AsynchronousChannelGroup.withThreadPool(threadPool);
            AsynchronousSocketChannel asyncSocketChannel = AsynchronousSocketChannel.open(asyncChannelGroup);
            asyncSocketChannel.setOption(StandardSocketOptions.TCP_NODELAY, true);

            ClientConnectionHandler connectionHandler = new ClientConnectionHandler(asyncSocketChannel);
            InetSocketAddress address = new InetSocketAddress("127.0.0.1", 9999);
            asyncSocketChannel.connect(address, null, connectionHandler);
        } catch (IOException e) {
            throw e;
        }
        serverStatus.await();
    }


}
