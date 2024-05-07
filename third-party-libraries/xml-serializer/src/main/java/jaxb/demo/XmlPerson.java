package jaxb.demo;

import jakarta.xml.bind.annotation.XmlRootElement;

// 配置XML文件根标签的名称, 默认为首字母小写
@XmlRootElement
public class XmlPerson {

    private String firstname;
    private String lastname;

    // TODO. 必须提供无参构造器，才能转换成XML文件
    public XmlPerson() {
    }

    public XmlPerson(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Override
    public String toString() {
        return "XmlPerson{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }
}
