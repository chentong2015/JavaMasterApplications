package com.java.others.XML.JAXB;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.ByteArrayInputStream;

// JAXB: Java Architecture for XML Binding
// 在Java Objects和XML files之间进行转换
public class BaseJaxbContext {

    // marshal: 将Java Object编组成指定的document xml文件
    // unmarshaller: 将xml file编组成指定的java objects
    private static JAXBContext context;

    public static void main(String[] args) {
        try {
            JAXBContext context = JAXBContext.newInstance(Person.class);
            Marshaller marshaller = context.createMarshaller();

            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            Person person = new Person("chen", "victor");

            // 第二个参数是要输出的outputStream
            marshaller.marshal(person, System.out);
        } catch (JAXBException ex) {
            ex.printStackTrace();
        }
    }

    public static void testXmlInput(byte[] byteArray) throws XMLStreamException, JAXBException {
        XMLInputFactory inputFactory = XMLInputFactory.newInstance();
        XMLStreamReader reader = inputFactory.createXMLStreamReader(new ByteArrayInputStream(byteArray));

        Unmarshaller unmarshaller = context.createUnmarshaller();
        unmarshaller.unmarshal(reader);
    }
}
