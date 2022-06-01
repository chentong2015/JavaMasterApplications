package com.java.others.Json;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.others.Json.model.Person;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class BaseJacksonMapperTree {

    // TODO. 从InputStream流中的指定路径下取出json节点，反序列化成指定的对象
    public Person testObjectMapperTree() {
        String jsonPath = "/persons/coder/master/";
        try (InputStream inputStream = new FileInputStream("test.txt")) {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(inputStream);
            JsonNode node = root.at(jsonPath);
            return mapper.treeToValue(node, Person.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
