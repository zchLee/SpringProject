package com.lea.di03;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author lzc
 * @create 2020.09.29 15:21
 */
@Component
public class School {

    @Value("武汉大学")
    private String name;
    @Value("武昌区珞珈山路")
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
