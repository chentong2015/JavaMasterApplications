package jaxb.record;

import jakarta.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

// TODO. 必须指定子标签的访问 @XmlAccessorType，才能解析到Object的属性
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        // 指定XML标签下的子标签，约束标签顺序
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

