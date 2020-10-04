package com.lea.di05;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("myStudent")
public class Student {

    @Value("张三")
    private String name;
    private Integer age;

    /*
        spring中通过注解给引用类型赋值，使用的是自动注入原理，支持byType，byName
        引用类型
                @AutoWirte:spring框架提供的注解，实现了引用类型的赋值。
                    属性：required 是一个boolean值类型 默认为true
                        required=true：表示引用类型赋值失败，程序报错并终止执行
                        required=false：表示引用类型赋值失败，程序程序正常执行，引用类型是null
                    默认使用byType自动注入
                    位置：1.在属性定义上面，无需set方法（推荐）
                         2.在set方法上

                    如果需要使用byName方法自动注入，需要做的是：
                        1.在属性上面加入@AutoWWrite
                        2.在属性上面加入@Qualifier(value="bean的Id") ： 表示使用指定bean的名称完成属性赋值
     */
    @Autowired(required=false)
    @Qualifier("lanxiang-notfind")
    private School school;

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
