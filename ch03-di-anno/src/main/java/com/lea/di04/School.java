package com.lea.di04;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author lzc
 * @create 2020.09.29 15:21
 */
@Component("lanxiang")
public class School {

    @Value("蓝翔技术学院")
    private String name;
    @Value("我也不知道在哪")
    private String address;

    public School() {}

    public School(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "School{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
