package org.example;

import org.example.utils.JsonConverter;

public class DemoJsonConverter {

    public static void main(String[] args) {
        JsonConverter jsonConverter = JsonConverter.getInstance();
        DemoObject myObject = new DemoObject("test name", 10, 20);

        System.out.println(jsonConverter.toJson(myObject));
        System.out.println(jsonConverter.toJson(new DemoObject()));
    }

    static class DemoObject {

        private String name;
        private int value1;
        private int value2;

        public DemoObject() {
        }

        public DemoObject(String name, int value1, int value2) {
            this.name = name;
            this.value1 = value1;
            this.value2 = value2;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getValue1() {
            return value1;
        }

        public void setValue1(int value1) {
            this.value1 = value1;
        }

        public int getValue2() {
            return value2;
        }

        public void setValue2(int value2) {
            this.value2 = value2;
        }
    }
}