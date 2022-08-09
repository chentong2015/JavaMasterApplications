package com.example.main;

import com.example.main.model.Project;
import org.reflections.Reflections;

import java.beans.JavaBean;
import java.util.Set;

import static org.reflections.scanners.Scanners.SubTypes;
import static org.reflections.scanners.Scanners.TypesAnnotated;

// Reflections
// - Java runtime metadata analysis: https://github.com/ronmamo/reflections
// - 从编译成class字节码(元数据)文件中执行反射，获取到类型的信息
// - 每次都需要重新编译，避免报错 Could not find or load main class
public class MainReflections {

    public static void main(String[] args) {
        Reflections reflections = new Reflections("com.example.main.model");

        // 反射：获取指定类型的继承类型
        Set<Class<?>> subTypes = reflections.get(SubTypes.of(Project.class).asClass());
        System.out.println(subTypes.stream().findFirst().get().getName());

        // 反射：获取添加了指定注解的java class
        Set<Class<?>> annotated = reflections.get(SubTypes.of(TypesAnnotated.with(JavaBean.class)).asClass());
        System.out.println(annotated.stream().findFirst().get().getName());
    }
}
