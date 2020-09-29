package com.lea.service.impl;

import com.lea.service.SomeService;

/**
 * @author lzc
 * @create 2020.09.29 11:48
 */
public class SomeServiceImpl implements SomeService {

    public SomeServiceImpl() {
        System.out.println("SomeServiceImpl构造方法执行了。");
    }

    @Override
    public void doSome() {
        System.out.println("执行了SomeService doSome()方法");
    }
}
