package jaxb.record;

import jakarta.xml.bind.annotation.*;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "name"
})
@XmlRootElement(name = "NameDetails")
public class NameDetails {

    @XmlElement(name = "Name", required = true)
    protected List<Name> name;

    public List<Name> getName() {
        return name;
    }
}