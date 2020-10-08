package org.example.aop01;

import org.example.aop01.SomeService;

/**
 * @author lzc
 * @create 2020-10-5 12:25
 */
// 目标类
public class SomeServiceImpl implements SomeService {
    @Override
    public void doSome(String name, Integer age) {
        // 给doSome增加功能
        System.out.println("目标方法doSome()...........");
    }
}
