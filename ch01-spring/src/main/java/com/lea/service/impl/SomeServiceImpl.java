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

    public void init(){
        System.out.println("对象初始化------");
    }

    public void destroy() {
        System.out.println("对象销毁----");
    }

    @Override
    public void doSome() {
        System.out.println("执行了SomeService doSome()方法");
    }
}
