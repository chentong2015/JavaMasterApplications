package org.example.utils;

import com.fasterxml.jackson.annotation.JsonIgnoreType;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import jakarta.xml.bind.JAXBElement;

import java.text.DateFormat;

// TODO. 设计模式: 将Jackson API封装到自定义的类型中，对外提供接口
public class JsonConverter {

    private final JsonMapper jsonMapper;

    // TODO. JsonMapper通过继承和Builder模式扩展了构建ObjectMapper对象的APIs
    private JsonConverter() {
        // 注意解析出来的json顺序可能不一致
        // 1. Making JSON serializer ignore null and empty objects
        // 2. Specifying object properties serialization order
        // 3. Unwrapping JAXBElement object
        jsonMapper = JsonMapper.builder()
                .serializationInclusion(JsonInclude.Include.NON_EMPTY)
                .configure(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, true)
                .addMixIn(JAXBElement.class, JAXBElementMixIn.class)
                .build();
        // The ObjectMapper APIs
        jsonMapper.setDateFormat(DateFormat.getDateInstance());
    }

    public static JsonConverter getInstance() {
        return new JsonConverter();
    }

    public JsonConverter ignore(Class<?> type) {
        addMixIn(type, MixIn.class);
        return this;
    }

    public JsonConverter ignore(Class<?> type, Class<?> mixInSource) {
        addMixIn(type, mixInSource);
        return this;
    }

    public String toJson(Object object) {
        return toCleanJson(object);
    }

    private void addMixIn(Class<?> target, Class<?> mixInSource) {
        jsonMapper.addMixIn(target, mixInSource);
    }

    private String toCleanJson(Object object) {
        try {
            return jsonMapper.writeValueAsString(object);
        } catch (JsonProcessingException ex) {
            throw new RuntimeException(ex);
        }
    }

    @JsonIgnoreType
    private abstract class MixIn {
    }

    private interface JAXBElementMixIn {
        @JsonValue
        Object getValue();
    }
}
