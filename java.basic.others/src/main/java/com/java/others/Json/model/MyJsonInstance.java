package com.java.others.Json.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MyJsonInstance {

    private final String name;
    private final String namespace;
    private MyJsonComponent component;
    
    @JsonCreator
    public MyJsonInstance(@JsonProperty("name") final String name,
                          @JsonProperty("namespace") String namespace,
                          @JsonProperty("component") MyJsonComponent component) {
        this.name = name;
        this.namespace = namespace;
        this.component = component;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("namespace")
    public String getNamespace() {
        return namespace;
    }

    public MyJsonComponent getComponent() {
        return component;
    }
}
