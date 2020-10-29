package com.lea.service;

import org.springframework.stereotype.Service;

/**
 * @author lzc
 * @create 2020-10-29 15:39
 */
@Service
public class HelloService {

    public String hello(String name) {
        return "success";
    }
}
