package com.lea.di01;

/**
 * @author lzc
 * @create 2020.09.29 14:49
 */
public class Student {

    private String name;

    private int age;

    public void setName(String name) {
        System.out.println("setName:" + name);
        this.name = name;
    }

    public void setAge(int age) {
        System.out.println("setAge:" + age);
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
