package com.lea.handler;

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

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("handler已经执行");
        // 通过代理对象执行方法时，会调用执行invoke()
        Object res = null;
        //
        Utils.log();
        // 执行目标类的方法，通过method类实现
        res = method.invoke(target, args); // 此处执行的是目标对象的方法，并返回结果
        Utils.doTrans();
        // 目标方法执行结果
        return res;
    }
}
