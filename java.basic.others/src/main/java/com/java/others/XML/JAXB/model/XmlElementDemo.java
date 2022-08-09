package com.java.others.XML.JAXB.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

// 匹配xml文件中指定的标签: 可将xml文件中信息映射到该对象Object
// <staticData>
//   <fromPath>xxx.path</fromPath>
//   ....
// </staticData>
@XmlRootElement(name = "staticData")
@XmlAccessorType(XmlAccessType.NONE)
public class XmlElementDemo {

    @XmlElement(required = true)
    private String name;

    // 指定xml的子标签
    @XmlElement(name = "fromPath")
    private String path;

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
