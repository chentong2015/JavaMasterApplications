package netty.basic_encode_decode;

import java.io.Serializable;

// ���ڸ����͵Ķ�����Ҫ��д��IO���������䣬��˱����ܹ����л�
public class DemoClass implements Serializable {

    private String name;

    public DemoClass(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
