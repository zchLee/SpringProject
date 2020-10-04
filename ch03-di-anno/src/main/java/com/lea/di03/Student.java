package com.lea.di03;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("myStudent")
public class Student {

    /*
    @Value:简单属性赋值
         属性： Value是String类型的，表示简单类型的书属性值
         位置：1.在属性定义的上面，无需set方法，推荐使用
              2.在set方法上
     */
    @Value("张三")
    private String name;
//    @Value("88")
    private Integer age;

    /*
        spring中通过注解给引用类型赋值，使用的是自动注入原理，支持byType，byName
        引用类型
                @AutoWirte:spring框架提供的注解，实现了引用类型的赋值。
                    默认使用byType自动注入
                    位置：1.在属性定义上面，无需set方法（推荐）
                         2.在set方法上
     */
    @Autowired
    private School school;

    @Value("780")
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
