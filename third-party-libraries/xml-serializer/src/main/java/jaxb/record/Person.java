package jaxb.record;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;
import jakarta.xml.bind.annotation.adapters.CollapsedStringAdapter;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement(name = "Person")
public class Person {

    // 透明数据, 不对其进行赋值
    @XmlTransient
    protected String resourceName;

    @XmlAttribute(name = "id", required = true)
    protected String id;

    @XmlAttribute(name = "action", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String action;

    @XmlAttribute(name = "date", required = true)
    protected String date;

    @XmlElement(name = "Gender")
    protected String gender;

    @XmlElement(name = "ActiveStatus")
    protected String activeStatus;

    @XmlElement(name = "NameDetails")
    protected NameDetails nameDetails;

    public String getResourceName() {
        return resourceName;
    }

    public String getGender() {
        return gender;
    }

    public String getActiveStatus() {
        return activeStatus;
    }

    public NameDetails getNameDetails() {
        return nameDetails;
    }
}
