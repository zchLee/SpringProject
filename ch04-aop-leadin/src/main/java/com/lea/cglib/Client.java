package com.lea.cglib;

import com.lea.utils.Utils;
import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author lzc
 * @create 2020-10-11 22:39
 */
public class Client {

    /**
     * 动态代理：
     *  特点：字节码随用随创建，随用随加载
     *  作用：不修改源码的基础上增强对方法的增强
     *  分类：
     *      基于接口的动态代理
     *      基于子类的动态代理
     *  基于子类的动态代理
     *      涉及的类：Enhancer
     *      提供者：第三方（cglib）
     *  如何创建代理对象：
     *      使用Enhancer中的create方法
     *  创建代理对象的要求：
     *      被代理类不能被final修饰
     *  create方法的参数：
     *      Class：字节码
     *          被代理对象的字节码
     *      Callback: 用于提供增强代码
     *          它是让我们写如何代理。我们是一般都是些一个该接口的实现类。通常情况下都是匿名内部类，但不是必须的
     *          一般写的都是该接口的子接口实现类：MethodInterceptor
     */
    public static void main(String[] args) {
        SomeServiceImpl cglibSomeService = (SomeServiceImpl) Enhancer.create(SomeServiceImpl.class, new MethodInterceptor() {
            /**
             *  执行被代理对象的任何方法都会经过此方法
             * @param o        代理对象（执行代理的类对象）
             * @param method   被代理对象执行方法
             * @param objects  被代理对象执行方法参数
             *    以上三个参数和基于接口的动态代理中的invoke方法参数是一样的
             * @param methodProxy：当前执行方法的代理对象
             * @return: java.lang.Object
             */
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                System.out.println("参数o是什么：" + o.getClass().getName());
                System.out.println("参数methodProxy是什么：" + methodProxy.getClass().getName());
                Object result = null;
                // 增加增强方法
                Utils.log();
                // 被代理对象(生产者/目标对象)执行 目标方法
                result = method.invoke(SomeServiceImpl.class.newInstance(), objects);
                return result;
            }
        });
        cglibSomeService.doOther();
    }
}
