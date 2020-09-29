package com.lea.di02;

/**
 * @author lzc
 * @create 2020.09.29 15:21
 */
public class School {

    private String name;

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
