package com.lea.di03;

/**
 * @author lzc
 * @create 2020.09.29 14:49
 */
public class Student {

    private String name;

    private int age;

    private School school;

    public Student() {}

    /*
    创建有参的构造方法，给spring通过有参构造注入方式给属性赋值
    （必须提供无参构造方法创建对象）
     */
    public Student(String myName, int age, School school) {
        this.name = myName;
        this.age = age;
        this.school = school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

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
                ", school=" + school +
                '}';
    }
}
