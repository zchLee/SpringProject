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
        // jdk代理对象实现AOP
        SomeServiceImpl target = new SomeServiceImpl();

        // 创建InvocationHandler 对象
        MyInvocationHandler handler = MyInvocationHandler.handlerFactory(target);
        /*
            ClassLoader loader,         类加载器
                它是用于加载代理对象的字节码的，和被代理对象使用相同的类加载器。固定写法
            Class<?>[] interfaces,      字节码数组，目标对象实现的接口
                它是用于让代理类和被代理类由相同的方法
            InvocationHandler h         用于提供增强的代码，代理对象要实现的功能
                它是让我们写如何代理。我们是一般都是些一个该接口的实现类。通常情况下都是匿名内部类，但不是必须的

            问题：
                此处依赖代理类的接口 Class<?>[] interfaces， 如果代理类没有接口，就无法代理
         */
        // 使用Proxy创建对象
        SomeService proxy = (SomeService) Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), handler);
        // 通过代理执行方法，会调用handler的invoke方法
        proxy.doSome();
    }

}
