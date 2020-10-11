package com.lea.proxy.service.impl;

import com.lea.proxy.service.SomeService;

/**
 * @author lzc
 * @create 2020-10-4 16:15
 */
public class SomeServiceImpl implements SomeService {
    @Override
    public void doSome() {
        System.out.println("生产一些东西。。。");
    }

    @Override
    public void doOther() {
        System.out.println("生产一些其他东西。。。");
    }
}
