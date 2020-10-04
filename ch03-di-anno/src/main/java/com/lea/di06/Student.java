package com.lea.di06;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;

@Component("myStudent")
public class Student {

    @Value("张三")
    private String name;
    private Integer age;

    /*
        引用类型
        @Resource:来自JDK中的注解，spring框架对这个注解提供了支持，可以使用它给引用类型赋值
            使用的是自动注入原理，支持byName， byType,默认byName
            位置：1、在属性上，无需set方法（推荐）
                2、在set方法上
         默认使用byName注入：先使用byName注入，如果自动注入失败再使用byType
     */
    @Resource
    public School school;

    @Value("78")
    public void setAge(Integer age) {
        System.out.println("年纪：" + age);
        this.age = age;
    }
//
//    public void setName(String name) {
//        this.name = name;
//    }


    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", school=" + school +
                '}';
    }
}
