package com.java.others.XML.w3c.dom;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class W3cDocumentDemo {

    private static Document document;
    private static Element classNode;

    public static void main(String[] args) {
        DOMImplementation domImplementation = getDocumentBuilder().getDOMImplementation();
        DocumentType docType = domImplementation.createDocumentType("hibernate-mapping",
                "-//Hibernate/Hibernate Mapping DTD 3.0//EN",
                "http://www.hibernate.org/dtd/hibernate-mapping-3.0.dtd");
        document = domImplementation.createDocument(null, "hibernate-mapping", docType);
        document.getDocumentElement().setAttribute("default-cascade", "none");
        document.getDocumentElement().setAttribute("default-access", "property");
        document.getDocumentElement().setAttribute("auto-import", "false");

        classNode = document.createElement("class");
        String className = W3cDocumentDemo.class.getName();
        classNode.setAttribute("table", "t_table");
        classNode.setAttribute("name", className);
        classNode.setAttribute("entity-name", className);

        document.getDocumentElement().appendChild(classNode);
        // System.out.println(document); 输出生成的xml file数据
    }

    private static DocumentBuilder getDocumentBuilder() {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setValidating(false);
            return factory.newDocumentBuilder();
        } catch (ParserConfigurationException ex) {
            throw new RuntimeException(ex);
        }
    }
}
