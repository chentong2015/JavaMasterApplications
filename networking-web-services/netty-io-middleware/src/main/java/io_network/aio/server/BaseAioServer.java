package io_network.aio.server;

import java.net.InetSocketAddress;
import java.net.StandardSocketOptions;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BaseAioServer {

    private static CountDownLatch serverStatus = new CountDownLatch(1);
    private static AsynchronousServerSocketChannel asyncServerSocketChannel;

    public static void main(String[] args) throws Exception {
        try {
            // �̳߳��е��̻߳�ȴ�IO�¼������֮�������߳�IO�ص�����(CompleteHandler)
            ExecutorService threadPool = Executors.newFixedThreadPool(10);
            AsynchronousChannelGroup asyncChannelGroup = AsynchronousChannelGroup.withThreadPool(threadPool);
            asyncServerSocketChannel = AsynchronousServerSocketChannel.open(asyncChannelGroup);
            asyncServerSocketChannel.setOption(StandardSocketOptions.SO_REUSEADDR, true);
            asyncServerSocketChannel.bind(new InetSocketAddress("127.0.0.1", 9999));
            System.out.println("Server started");
        } catch (Exception e) {
            System.out.println("Server establish error!");
            throw e;
        }
        // �÷�ʽ���׳�java.nio.channels.AcceptPendingException�쳣��ֻ��һ�����ӽ���֮����һ�����Ӳ��ܽ���
        // while (true) {
        //   asyncServerSocketChannel.accept();
        // }
        //
        // ����ͨ��Future�첽�ķ�ʽȡ�������.get()��������ǰ�߳�
        // while (true) {
        //     try {
        //         Future<AsynchronousSocketChannel> acceptFuture = asyncServerSocketChannel.accept();
        //         AsynchronousSocketChannel asyncSocketChannel = acceptFuture.get();
        //         asyncHandle(asyncSocketChannel);
        //     } catch (Exception e) {
        //         e.printStackTrace();
        //     }
        // }

        // ʹ�ûص���ʽ������
        asyncServerSocketChannel.accept(asyncServerSocketChannel, new ServerConnectionHandler());

        // Since the methods of AIO are returned directly, a lock must be used here to avoid thread exit and service stop
        // After the request is initiated, the current thread can do other things,and will be notified when the request is completed
        // As a full-time server, the main thread actually has nothing else to do, maybe the client is more suitable for using AIO
        serverStatus.await();
    }
}
