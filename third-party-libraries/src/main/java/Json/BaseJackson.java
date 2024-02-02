package json;

import json.model.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class BaseJackson {

    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        MyInstance instance = new MyInstance("test", List.of("item1", "item2"));

        List<String> copyServices = instance.getServices();
        System.out.println(copyServices);
        // copyServices.remove(1);
        System.out.println(objectMapper.writeValueAsString(instance));
        System.out.println(copyServices);
    }

    // Test Json Object conversion
    public void testJsonObject() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        MyJsonComponent component = new MyJsonComponent("component name");
        System.out.println(objectMapper.writeValueAsString(component));

        MyJsonInstance instance = new MyJsonInstance("name", "namespace", component);
        System.out.println(objectMapper.writeValueAsString(instance.getName()));
    }

    public void testJsonIncludeNotNull() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Person p = new Person();
        System.out.println(mapper.writeValueAsString(p));
        // output: {"firstName":"Mark", "lastName":"Watney"}
    }

    public void testJsonIgnoreProperties() throws JsonProcessingException {
        String jsonInput = "{\n" +
                "   \"firstName\": \"Homer\",\n" +
                "   \"middleName\": \"Jay\",\n" +
                "   \"lastName\": \"Simpson\"\n" +
                "}";
        ObjectMapper mapper = new ObjectMapper();
        Student student = mapper.readValue(jsonInput, Student.class);
        // student只会解析到它有效的字段
        System.out.println(student.firstName);
        System.out.println(student.lastName);
    }
}
