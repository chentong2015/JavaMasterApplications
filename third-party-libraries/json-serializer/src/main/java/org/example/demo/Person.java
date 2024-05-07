package org.example.demo;

import com.fasterxml.jackson.annotation.JsonInclude;

// 在对象序列化成json的过程中，忽略null属性值的序列化
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Person {

    public String firstName = "chen";
    public String middleName;
    public String lastName = "tong";
}
