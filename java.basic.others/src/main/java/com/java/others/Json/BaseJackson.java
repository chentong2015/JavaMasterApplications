package com.java.others.Json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.others.Json.model.MyJsonComponent;
import com.java.others.Json.model.MyJsonInstance;
import com.java.others.Json.model.Person;
import com.java.others.Json.model.Student;

public class BaseJackson {

    // output: {"firstName":"Mark", "lastName":"Watney"}
    public void testJsonIncludeNotNull() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Person p = new Person();
        System.out.println(mapper.writeValueAsString(p));
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

    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        MyJsonComponent component = new MyJsonComponent("component name");
        System.out.println(objectMapper.writeValueAsString(component));

        MyJsonInstance instance = new MyJsonInstance("name", "namespace", component);
        System.out.println(objectMapper.writeValueAsString(instance.getName()));
    }
}
