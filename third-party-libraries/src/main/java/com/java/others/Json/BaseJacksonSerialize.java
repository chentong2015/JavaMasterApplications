package com.java.others.Json;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

// Jackson序列化和反序列化的设置
@JsonSerialize
// @JsonDeserialize(builder = )
public interface BaseJacksonSerialize {

    @JsonProperty("id")
    default String getId() {
        return "0";
    }

    @JsonProperty("label")
    default String getLabel() {
        return "";
    }
}
