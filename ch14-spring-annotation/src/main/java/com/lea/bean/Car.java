package com.lea.bean;

/**
 * @author lzc
 * @create 2020-10-21 14:22
 */
public class Car {

    public Car() {
        System.out.println("构造器被调用");
    }

    public void init() {
        System.out.println("bean 被初始化");
    }

    public void destroy() {
        System.out.println("bean 被销毁----");
    }
}
