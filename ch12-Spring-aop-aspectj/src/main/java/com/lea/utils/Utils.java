package com.lea.utils;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * @author lzc
 * @create 2020-10-4 16:30
 *
 * 提供增强方法
 *  让其方法在切入带你执行之前执行（切入点就是增强的业务方法）
 */
public class Utils {

    public void log() {
        System.out.println("打印日志-------------------");
    }

    public void doTrans() {
        System.out.println("成功执行到此，提交事务");
    }

    public void before() {
        System.out.println("=====================before前置通知方法执行======================");
    }

    public void after() {
        System.out.println("=====================after后置通知方法执行======================");
    }

    public void afterThrow() {
        System.out.println("=====================afterThrow异常置通知方法执行======================");
    }

    public void afterFinally() {
        System.out.println("=====================afterFinally最终通知置通知方法执行======================");
    }

    /**
     * 环绕通知：
     *  问题：
     *      当配置环绕通知后，切入点方法没有执行，而通知方法执行了
     *  为什么？
     *      对比动态代理中的环绕通知方法，发现动态代理的环绕通知有明确的切入点方法调用，而此方法没有
     *  怎么解决：
     *      Spring框架为我们提供了个接口：ProceedingJoinPoint.该框架有个方法processed()，此方法就相当于明确调用
     *      切入方法。该接口可以作为环绕通知的方法参数，在程序执行时，spring框架会为我们提供该接口的实现类供我们使用
     *
     *  spring中的环绕通知，
     *      它是spring框架为我们提供的一种可以在代码中手动控制增强方法何时执行的方式。
     *
     *  事务就是用的环绕通知，在前置通知中关闭自动提交，开始事务
     *  后置通知中提交事务
     *  异常通知中回滚事务
     *  最终通知中，还可以修改返回值
     */
    public Object around(ProceedingJoinPoint pjp) {
        Object result = null;
        try {
            System.out.println("=====================around环绕  前置  通知方法执行======================");

            Object[] args = pjp.getArgs(); // 方法执行传入的参数
            result = pjp.proceed(args);// 明确调用业务层方法

            System.out.println("=====================around环绕  后置  通知方法执行======================");
            return result;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            System.out.println("=====================around环绕  异常  置通知方法执行======================");
        } finally {
            System.out.println("=====================around环绕  最终  通知方法执行======================");
        }
        return result;
    }
}
