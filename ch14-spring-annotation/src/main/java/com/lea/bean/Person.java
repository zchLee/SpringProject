package com.lea.bean;

import org.springframework.beans.factory.annotation.Value;

/**
 * @author lzc
 * @create 2020-10-21 16:09
 *
 * @Value注解的使用
 */
public class Person {
    /**
     * 使用@Value赋值
     *  1.接班类型和String
     *  2.SringEL表达式 #{}
     *  3. 可以写${}; 取出配置文件（properties）中的值（在运行环境变量中的值）
     *      // 从配置文件中的k/v保存到运行的环境变量中，加载完外部的配置文件后，可以用${}取值
     */

    @Value("张三")
    private String name;
    @Value("#{20-3}")
    private String age;
    @Value("${person.nickname}")
    private String nickname;

    public Person() {
        System.out.println("person构造方法被调用了");
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
