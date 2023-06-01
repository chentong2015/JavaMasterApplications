package com.java.others.Json;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.others.Json.model.Person;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

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

    public static void main(String[] args) throws IOException {
        String value = "{\n" +
                "   \"displayName\":\"The Hello\",\n" +
                "   \"description\":\"This is a sample\",\n" +
                "   \"integrationPoints\":[\n" +
                "      \"maintenance\",\n" +
                "      \"test\"\n" +
                "   ]\n" +
                "}";
        InputStream inputStream = new ByteArrayInputStream(value.getBytes(StandardCharsets.UTF_8));

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(inputStream);
        if (jsonNode.has("integrationPoints")) {
            System.out.println(jsonNode.get("integrationPoints"));

            boolean check = jsonNode.get("integrationPoints").isArray();
            System.out.println(check);

            List<String> intPoints = objectMapper.readValue(jsonNode.get("integrationPoints").traverse(), new TypeReference<>() {
            });
            System.out.println(intPoints.contains("maintenance"));
        }
    }
}
