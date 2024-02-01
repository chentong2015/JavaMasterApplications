package tomcat.config;

import org.apache.catalina.*;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.core.StandardEngine;
import org.apache.catalina.core.StandardHost;
import org.apache.catalina.startup.Tomcat;

public class TomcatStarter {

    private static String hostname = "localhost";
    private static int port = 8081;

    // ʹ�ô���������Tomcat����Ч��server.xml�ļ�����
    public static void startTomcat() {
        Tomcat tomcat = new Tomcat();
        tomcat.getHost().setAutoDeploy(false);

        // getServer(): ����һ��Standard Server��Ȼ�󴴽�Service����ӵ�Server��
        Server server = tomcat.getServer();
        Service service = server.findService("Tomcat");

        Connector connector = new Connector();
        connector.setPort(port);
        // connector.setAsyncTimeout(1000L); ���������첽�ĳ�ʱʱ��
        service.addConnector(connector);

        String contextPath = "";
        Context context = new StandardContext();
        context.setPath(contextPath);
        // TODO: ���ﲻ��ʹ�û���Xml���õ�Listener
        // context.addLifecycleListener(new Tomcat.DefaultWebXmlListener());
        context.addLifecycleListener(new Tomcat.FixContextListener());

        Host host = new StandardHost();
        // �����������host����
        host.setName(hostname);
        host.addChild(context);

        Engine engine = new StandardEngine();
        engine.setDefaultHost(hostname);
        engine.addChild(host);
        service.setContainer(engine);

        // ������Ҫ���Servlet���ڷַ����� ==> Spring Webʹ��DispatcherServlet
        // tomcat.addServlet(contextPath, "dispatcher", new MyDispatcherServlet());
        // ����ӳ���ϵ������������Ҫ�ߵ�Servlet
        context.addServletMappingDecoded("/*", "dispatcher");

        try {
            tomcat.start();
            System.out.println("tomcat started at " + hostname + ":" + port);
            tomcat.getServer().await();
        } catch (LifecycleException ex) {
            ex.printStackTrace();
        }
    }
}
