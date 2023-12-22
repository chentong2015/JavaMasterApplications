package com.java.others.XML.JAXB;

import com.java.others.XML.JAXB.model.Person;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.ByteArrayInputStream;

// JAXB: Java Architecture for XML Binding
// 1. 在Java Objects和XML files之间进行转换
// 2. Hibernate源码中也引用了该dependency
public class BaseJaxbContext {

    // marshal: 将Java Object编组成指定的document xml文件
    // unmarshaller: 将xml file编组成指定的java objects
    public static void main(String[] args) {
        try {
            JAXBContext context = JAXBContext.newInstance(Person.class);
            Marshaller marshaller = context.createMarshaller();

            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            Person person = new Person("chen", "victor");

            // 第二个参数是要输出的outputStream
            // <?xml version="1.0" encoding="UTF-8" standalone="yes"?>
            // <person>
            //    <firstname>chen</firstname>
            //    <lastname>victor</lastname>
            // </person>
            marshaller.marshal(person, System.out);
        } catch (JAXBException ex) {
            ex.printStackTrace();
        }
    }

    public static void testXmlInput(byte[] byteArray) throws XMLStreamException, JAXBException {
        XMLInputFactory inputFactory = XMLInputFactory.newInstance();
        XMLStreamReader reader = inputFactory.createXMLStreamReader(new ByteArrayInputStream(byteArray));

        JAXBContext context = JAXBContext.newInstance(Person.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        unmarshaller.unmarshal(reader);
    }
}
