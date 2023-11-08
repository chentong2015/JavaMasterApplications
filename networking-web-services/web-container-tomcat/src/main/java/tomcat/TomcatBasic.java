package tomcat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// Tomcat 7.0�汾: ֧��BIO, NIO
// Tomcat 8.0�汾: ֧��NIO
// Tomcat 9.0�汾(��֮ǰ): ֧��Java EE, ʵ��javax.servlet�ӿ�
// Tomcat 10.0�汾(��֮��): ֧��JakartaEE��ʵ��jakarta.servlet.Servlet�ӿ�

// Tomcat��һ��Servlet����
//   Servlet(�����Ӧ�ó���) = server+applet(java������Ӧ�ó���������������ˣ��ͻ���)
//   Servlet��һ���淶: �Զ���ʵ����HttpServlet������(����ʵ�ֵĽӿ�Servlet Interface)
//   Servlet��һ��С����wrapper -> Ӧ��������Context -> ������host���� -> ʹ��Engine����������
//   TODO: Wrapper�����
//   public class MyServlet extends HttpServlet implements SingleThreadModel {}
//   һ���Զ���ʵ����HttpServlet���͵��࣬�ڲ����ʱ�����е������ǹ���ͬһ��Servlet
//   ���ʵ����SingleThreadModel�ӿڣ����ʾÿһ��������̶߳�������ʹ��һ��Servletʵ��
//   ʹ��Wrapper����װ���е�Servletʵ��

// Tomcat��ΪHttp Server��Ĭ�϶˿ں�8080
// Tomcat������ʽ: cmd>apache-tomcat-8.5.65\bin\startup.bat
public class TomcatBasic extends HttpServlet {
    
    // TODO: Tomcat��������(�ַ�����)�߼�
    // ֱ�ӵ����Զ���servlet.service()��HttpServlet��ʵ�ֵķ���
    // > ȷ���������������
    // > ����doGet()����doPost()
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
