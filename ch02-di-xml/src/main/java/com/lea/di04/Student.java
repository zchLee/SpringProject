package com.lea.di04;


/**
 * @author lzc
 * @create 2020.09.29 14:49
 */
public class Student {

    private String name;

    private Integer age;

    private School school;

    public void setSchool(School school) {
        this.school = school;
    }

    public void setName(String name) {
        System.out.println("setName:" + name);
        this.name = name;
    }

    public void setAge(Integer age) {
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
