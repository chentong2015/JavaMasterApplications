package com.java.others.XML;

import org.w3c.dom.*;

import java.util.Arrays;
import java.util.List;

public class XMLDocumentDemo {

    // 从XML文件中获取到DOM格式的文件，通过DOM(Document Object Model)来进行配置
    public void testXMLDocument(Document domDocument) {
        Attr newAttr = domDocument.createAttribute("newAttr");
        newAttr.setValue("new value");

        // 拿到dom文档中第一个指定标签的node节点
        Node node = domDocument.getElementsByTagName("<tagName").item(0);

        // 拿到标签上指定的属性名称的值
        String attrValue = node.getAttributes().getNamedItem("attrName").getNodeValue();
        node.getAttributes().item(0).getAttributes().setNamedItem(newAttr);
    }

    private void adaptHbmFileWithEntityNameTag(Document domDocument) throws Exception {
        List<String> elementTagNames = Arrays.asList("class", "subclass", "joined-subclass", "union-subclass");
        Node packageNode = domDocument.getElementsByTagName("hibernate-mapping").item(0).getAttributes().getNamedItem("package");
        for (String elementTag : elementTagNames) {
            NodeList nodeList = domDocument.getElementsByTagName(elementTag);
            if (nodeList != null) {
                addEntityNameTag(packageNode, nodeList);
            }
        }
    }

    private void addEntityNameTag(Node packageNode, NodeList nodeList) {
        for (int index = 0; index < nodeList.getLength(); index++) {
            Node node = nodeList.item(index);
            if (node.getAttributes().getNamedItem("entity-name") == null) {
                String name = node.getAttributes().getNamedItem("name").getNodeName();
                if (packageNode != null) {
                    name = packageNode.getNodeName().concat(".").concat(name);
                }
                // node.getAttributes().setNamedItem(new AttributeNode("entity-name", name));
                ((Element) node).setAttribute("entity-name", name);
            }
        }
    }
}
