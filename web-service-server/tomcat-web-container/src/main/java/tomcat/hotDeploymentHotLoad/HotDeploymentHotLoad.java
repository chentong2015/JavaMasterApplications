package tomcat.hotDeploymentHotLoad;

// Tomcat�Ȳ�����ȼ���: �ڲ�����Tomcat������£�ʹ��Ӧ�õ����´���(�䶯)��Ч
// TODO: /WEB-INF���ļ���������ļ�����(�޸�ʱ��)�����仯����Ӱ���������������ļ��б䶯(�޸�ʱ��)
// 1. �Ȳ����ʾ���²������ã���ִ�е�������Host����
//    <Host autoDeploy="true"> �Ȳ���Ĭ���Ǵ򿪵�
//    ��������������ļ���/ProjectDemo�������Ƿ����˱仯 ==> ���ļ��е��޸�ʱ��
//
// 2. �ȼ��ر�ʾ���¼���class����ִ�е�������Context����ʾӦ��
//    <Context reloadable="false"> �ȼ���Ĭ���ǹرյ�
//    �������������ļ��µ�/classesĿ¼�µ�/classes�ļ���/lib��jar���ı仯 ==> ���ļ��е��޸�ʱ��
public class HotDeploymentHotLoad {

    // ������²��������������Context����
    // �ȼ��أ�������¼��أ���������Context����
    // StandardContext.java > reload()

}
