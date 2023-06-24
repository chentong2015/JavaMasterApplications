package com.example.main;

import com.example.main.data.MyEntityData;
import com.example.main.model.Project;
import org.reflections.Reflections;
import org.reflections.scanners.Scanners;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

import java.beans.JavaBean;
import java.util.Set;

import static org.reflections.scanners.Scanners.SubTypes;
import static org.reflections.scanners.Scanners.TypesAnnotated;

public class MainReflections {

    public static void main(String[] args) {
        String dtoPackageName = MyEntityData.class.getPackageName();
        // ConfigurationBuilder configurationBuilder = new ConfigurationBuilder()
        //         .forPackage(dtoPackageName)
        //         .addScanners(Scanners.TypesAnnotated)
        //         .addScanners(Scanners.FieldsAnnotated)
        //         .addScanners(Scanners.MethodsAnnotated)
        //         .filterInputsBy(new FilterBuilder().includePackage(dtoPackageName));
        // Reflections reflections = new Reflections(configurationBuilder);

        // 在扫描具有特殊注解的的类型时，需要添加package路径的过滤器，否则会全模块扫描
        ConfigurationBuilder configurationBuilder = new ConfigurationBuilder()
                .forPackage(dtoPackageName)
                .setScanners(Scanners.TypesAnnotated, Scanners.FieldsAnnotated, Scanners.MethodsAnnotated)
                .filterInputsBy(new FilterBuilder().includePackage(dtoPackageName));
        Reflections reflections = new Reflections(configurationBuilder);
        System.out.println("done");
    }

    private static void testReflections() {
        Reflections reflections = new Reflections("com.example.main.model");

        // 反射：获取指定类型的继承类型
        Set<Class<?>> subTypes = reflections.get(SubTypes.of(Project.class).asClass());
        System.out.println(subTypes.stream().findFirst().get().getName());

        // 反射：获取添加了指定注解的java class
        Set<Class<?>> annotated = reflections.get(SubTypes.of(TypesAnnotated.with(JavaBean.class)).asClass());
        System.out.println(annotated.stream().findFirst().get().getName());
    }

    // A filter tells the scanners what to include and what to exclude when scanning the classpath.
    private static void testReflectionFilter() {
        FilterBuilder filterBuilder = new FilterBuilder()
                .includePackage("com.reflections")
                .excludePackage("com.reflections.test");

        Reflections reflections = new Reflections(new ConfigurationBuilder()
                .setUrls(ClasspathHelper.forPackage("com.example.reflections"))
                .setScanners(SubTypes, TypesAnnotated)
                .filterInputsBy(filterBuilder));
        System.out.println(reflections);
    }
}
