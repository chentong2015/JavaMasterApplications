package jaxb;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import jaxb.demo.XmlPerson;
import jaxb.record.Person;
import jaxb.record.Records;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.InputStream;

// JAXB: Java XML Binding
// 提供XML文件到Object转换，使用在Hibernate项目中
public class JaxbContextDemo {

    public static void main(String[] args) throws JAXBException, XMLStreamException {
        writeXml();

        ClassLoader loader = JaxbContextDemo.class.getClassLoader();
        InputStream inputStream = loader.getResourceAsStream("demo.xml");
        readXml(inputStream, XmlPerson.class);

        inputStream = loader.getResourceAsStream("records.xml");
        readXml(inputStream, Records.class);
    }

    // marshal: 将Object编组成指定的document xml文件
    public static void writeXml() throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(XmlPerson.class);
        Marshaller marshaller = context.createMarshaller();

        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        XmlPerson person = new XmlPerson("chen", "victor");
        marshaller.marshal(person, System.out);
    }

    // unmarshaller: 将xml file编组成指定的Object
    // TODO. 必须创建对应的Object类型才能转换对应的XML文件
    public static void readXml(InputStream inputStream, Class<?> clazz) throws XMLStreamException, JAXBException {
        XMLInputFactory inputFactory = XMLInputFactory.newInstance();
        XMLStreamReader reader = inputFactory.createXMLStreamReader(inputStream);

        JAXBContext context = JAXBContext.newInstance(clazz);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        Object object = unmarshaller.unmarshal(reader);
        System.out.println(object);
    }
}
