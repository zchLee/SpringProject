package com.lea;

import com.lea.handler.MyInvocationHandler;
import com.lea.service.SomeService;
import com.lea.service.impl.SomeServiceImpl;

import java.lang.reflect.Proxy;

/**
 * @author lzc
 * @create 2020-10-4 16:38
 */
public class Main {

    public static void main(String[] args) {
        // jdk代理对象实行AOP
        SomeServiceImpl target = new SomeServiceImpl();

        // 创建InvocationHandler 对象
        MyInvocationHandler handler = MyInvocationHandler.handlerFactory(target);
        /*
            ClassLoader loader,         目标对象的构造器
            Class<?>[] interfaces,      目标对象实现的接口
            InvocationHandler h         代理对象要实现的功能
         */
        // 使用Proxy创建对象
        SomeService proxy = (SomeService) Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), handler);
        // 通过代理执行方法，会调用handler的invoke方法
        proxy.doSome();
    }

}
