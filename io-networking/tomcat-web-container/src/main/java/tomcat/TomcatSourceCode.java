package tomcat;

public class TomcatSourceCode {

    // ����Server.xml��<Connector>���ã�ʹ�ò�ͬ��IOģ��
    // public class Connector extends LifecycleMBeanBase
    //   if ("HTTP/1.1".equals(protocol)) {
    //       setProtocolHandlerClassName
    //       ("org.apache.coyote.http11.Http11Protocol");
    //   } else if ("AJP/1.3".equals(protocol)) {
    //       setProtocolHandlerClassName
    //       ("org.apache.coyote.ajp.AjpProtocol");
    //   } else if (protocol != null) {
    //       ָ������������·��
    //       setProtocolHandlerClassName(protocol);
    //   }

    // Tomcat��HTTPЭ���ʵ���ࣺ
    // 1. Http11Protocol ֧��BIOģ��
    //    public Http11Protocol() {
    //        endpoint = new JIoEndpoint(); �˵�, JIO -> BIO
    //        cHandler = new Http11ConnectionHandler(this);
    //        ((JIoEndpoint) endpoint).setHandler(cHandler);
    //        setSoLinger(Constants.DEFAULT_CONNECTION_LINGER);
    //        setSoTimeout(Constants.DEFAULT_CONNECTION_TIMEOUT);
    //        setTcpNoDelay(Constants.DEFAULT_TCP_NO_DELAY);
    //    }
    //
    //    public class JIoEndpoint {
    //        �ڶ˵��п���һ���̣߳�ʹ��BIO��ȡ����
    //        protected class Acceptor extends AbstractEndpoint.Acceptor {
    //           socket = serverSocketFactory.acceptSocket(serverSocket);
    //           processSocket(Socket socket); ����socket������
    //        }
    //    }
    //
    // 2. Http11NioProtocol ֧��NIOģ��
    //    public class NIoEndpoint {
    //       �ڶ˵��п���һ���̣߳�ʹ��NIO��ȡ����
    //       protected class Acceptor extends AbstractEndpoint.Acceptor {
    //           SocketChannel socket = null;
    //           try {
    //             // Accept the next incoming connection from the server socket
    //             socket = serverSock.accept();
    //       }
    //    }

    // Tomcatͨ��IOģ��(NIO)��Socket�л�ȡ���ݣ�Ȼ����н���  ===> ���շ�װ��Request !!
    //    protected boolean processSocket(Socket socket) {
    //        // Process the request from this socket
    //        try {
    //            SocketWrapper<Socket> wrapper = new SocketWrapper<Socket>(socket);
    //            wrapper.setKeepAliveLeft(getMaxKeepAliveRequests());
    //            wrapper.setSecure(isSSLEnabled());
    //            // During shutdown, executor may be null - avoid NPE
    //            if (!running) {
    //                return false;
    //            }
    //            TODO: ʹ���̳߳ؽ��д��� ==> �����̳߳صĴ�С
    //            getExecutor().execute(new SocketProcessor(wrapper));
    //        } catch (RejectedExecutionException x) {
    //            log.warn("Socket processing request was rejected for:"+socket,x);
    //            return false;
    //        } catch (Throwable t) {
    //            ExceptionUtils.handleThrowable(t);
    //            // This means we got an OOM or similar creating a thread, or that
    //            // the pool and its queue are full
    //            log.error(sm.getString("endpoint.process.fail"), t);
    //            return false;
    //        }
    //        return true;
    //    }
    //
    // �����������ݵ����� AbstractHttp11Processor<S>
    //    public SocketState process(SocketWrapper<S> socketWrapper) {
    //       getInputBuffer().parseRequestLine(keptAlive) ����������
    //       getInputBuffer().parseHeaders() ��������ͷ
    //       ����������(���͵�����)
    //    }
}
