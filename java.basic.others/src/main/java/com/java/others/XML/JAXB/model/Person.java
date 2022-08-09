package com.java.others.XML.JAXB.model;

import jakarta.xml.bind.annotation.XmlRootElement;

// Object对应xml文件数据
// <?xml version="1.0" encoding="UTF-8" standalone="yes"?>
// <person>
//    <firstname>chen</firstname>
//    <lastname>victor</lastname>
// </person>

@XmlRootElement
public class Person {

    private String firstname;
    private String lastname;

    // 必须提供无参构造器，才能转换成XML文件
    public Person() {
    }

    public Person(String firstname, String lastname) {
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
}
