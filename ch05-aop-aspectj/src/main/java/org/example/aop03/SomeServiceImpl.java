package org.example.aop03;

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

    @Override
    public String doOther(String name, Integer age) {
        System.out.println("目标方法doOther()-----");
        return "abcd";
    }

    @Override
    public String doFirst(String name, Integer age) {
        System.out.println("目标方法doFirst（）执行-----");
        return "doFirst";
    }

}
