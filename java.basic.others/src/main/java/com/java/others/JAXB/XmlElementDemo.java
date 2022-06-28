package com.java.others.JAXB;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "staticData")
public class XmlElementDemo {

    @XmlElement(required = true)
    private String name;

    // JAXB必须提供无参构造器
    public XmlElementDemo() {

    }

    public XmlElementDemo(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
