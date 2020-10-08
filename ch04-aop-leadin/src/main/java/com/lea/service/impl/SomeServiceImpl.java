package com.lea.service.impl;

import com.lea.service.SomeService;

/**
 * @author lzc
 * @create 2020-10-4 16:15
 */
public class SomeServiceImpl implements SomeService {
    @Override
    public void doSome() {
        System.out.println("执行业务方法odSome。。。");
    }

    @Override
    public void doOther() {
        System.out.println("执行业务方法doOther。。。");
    }
}
