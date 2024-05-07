package jaxb.record;

import jakarta.xml.bind.annotation.*;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "nameValue"
})
@XmlRootElement(name = "Name")
public class Name {

    @XmlElement(name = "NameValue", required = true)
    protected List<NameValue> nameValue;

    @XmlAttribute(name = "NameType", required = true)
    protected String nameType;

    public void setNameValue(List<NameValue> nameValue) {
        this.nameValue = nameValue;
    }

    public void setNameType(String nameType) {
        this.nameType = nameType;
    }
}

