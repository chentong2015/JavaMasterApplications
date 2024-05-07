package org.example.demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;

public class TestJsonObjects {

    // 2.10以前低版本的APIs
    // JsonMapper jsonMapper = new JsonMapper();
    // jsonMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
    // jsonMapper.configure(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, true);
    // jsonMapper.addMixIn(JAXBElement.class, JAXBElementMixIn.class);
    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        MyInstance instance = new MyInstance("test", List.of("item1", "item2"));

        System.out.println(objectMapper.writeValueAsString(instance));

        MyJsonComponent component = new MyJsonComponent("component name");
        System.out.println(objectMapper.writeValueAsString(component));

        MyJsonInstance jsonInstance = new MyJsonInstance("name", "namespace", component);
        System.out.println(objectMapper.writeValueAsString(jsonInstance.getName()));

        // 通过Object添加的注解可以忽略对null属性的序列化
        Person p = new Person();
        System.out.println(objectMapper.writeValueAsString(p));
    }

    // 忽略解析不到的属性字段, 只解析Object的有效字段
    public void testJsonIgnoreProperties() throws JsonProcessingException {
        String jsonInput = "{\n" +
                "   \"firstName\": \"Homer\",\n" +
                "   \"middleName\": \"Jay\",\n" +
                "   \"lastName\": \"Simpson\"\n" +
                "}";
        ObjectMapper mapper = new ObjectMapper();
        Student student = mapper.readValue(jsonInput, Student.class);
        System.out.println(student.firstName);
        System.out.println(student.lastName);
    }
}
