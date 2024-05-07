package jaxb.record;

import jakarta.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

// 指定XML标签下的子标签，约束标签顺序
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "person"
})
@XmlRootElement(name = "Records")
public class Records {

    @XmlElement(name = "Person")
    protected List<Person> person;

    public List<Person> getPerson() {
        if (person == null) {
            person = new ArrayList<>();
        }
        return person;
    }
}

