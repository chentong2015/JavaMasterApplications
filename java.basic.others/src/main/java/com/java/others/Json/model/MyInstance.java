package com.java.others.Json.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

import static java.util.Objects.requireNonNull;

public class MyInstance {

    private final String name;
    private final List<String> services;

    @JsonCreator
    public MyInstance(@JsonProperty("name") final String subsystemName,
                      @JsonProperty("services") List<String> services) {
        this.name = requireNonNull(subsystemName);
        this.services = requireNonNull(services);
    }

    // 标记注解的方法将会同时作用在Json<->Object之间的转换上
    @JsonProperty("subsystemName")
    public String getSubsystem() {
        return name;
    }

    @JsonProperty("services")
    public List<String> getServices() {
        return services;
        // return Collections.unmodifiableList(services);
    }
}
