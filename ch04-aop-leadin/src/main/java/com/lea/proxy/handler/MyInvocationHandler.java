package com.lea.proxy.handler;

import com.lea.utils.Utils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author lzc
 * @create 2020-10-4 16:31
 */
public class MyInvocationHandler implements InvocationHandler {

    // 动态代理：目标对象
    private Object target;

    public static MyInvocationHandler handlerFactory(Object target) {
        MyInvocationHandler invocationHandler = new MyInvocationHandler();
        invocationHandler.target = target;
        return invocationHandler;
    }

//    public MyInvocationHandler(Object target) {
//        this.target = target;
//    }

    /**
     * @author: lzc
     * @date: 2020-10-11 22:23
     * @param proxy  代理对象的引用(指定代理的类)
     * @param method 当前执行的方法
     * @param args   当前执行方法的参数
     * @return Object 和被代理对象有相同的返回值
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("proxy是什么东西：" + proxy.getClass().getName());
        // 通过代理对象执行方法时，会调用执行invoke()
        Object res = null;
        // 增强方法
        Utils.log();
        // 执行目标类（被代理类）的方法，通过method类实现
        res = method.invoke(target, args); // 此处执行的是目标对象的方法，并返回结果
        // 增强方法
        Utils.doTrans();
        // 目标方法执行结果
        return res;
    }
}
