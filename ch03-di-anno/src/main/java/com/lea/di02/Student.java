package com.lea.di02;

import com.lea.di03.School;
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
//    @Value("张三")
    @Value("${name}")
    private String name;
//    @Value("88")
    @Value("${age}")
    private Integer age;

//    @Value("780")
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
                '}';
    }
}
