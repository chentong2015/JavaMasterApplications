package tomcat.policy;

// cmd������ʽ��apache-tomcat-8.5.65\bin\startup.bat -security
// �򿪰�ȫ���ƣ�ͨ��conf/catalina.policy�ļ����Ȩ��
public class TomcatPolicy {

    //  System.exit(1); ��doGet()�������Ե��ø÷���
    //
    //  public static void exit(int status) {
    //     Runtime.getRuntime().exit(status);
    //  }
    //  public void exit(int status) {
    //      SecurityManager security = System.getSecurityManager();
    //      if (security != null) {
    //          security.checkExit(status);
    //      }
    //      Shutdown.exit(status);
    //  }
    //
    //  ��֤��ǰ�����Ƿ����˳�VM��Ȩ��"exitVM.", û����ᱨ��������ִ��exit(1)�˳�
    //  public void checkExit(int status) {
    //      checkPermission(new RuntimePermission("exitVM."+status));
    //  }

    // Ĭ����û�и�Ȩ�޵ģ���Ҫ���ļ������ָ����Ȩ������
    // permission java.lang.RuntimePermission "exitVM.1";
}
