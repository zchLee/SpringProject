package org.example.aop04;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import java.util.Arrays;
import java.util.Date;

/**
 * @author lzc
 * @create 2020-10-5 12:29
 *
 * @Aspect: 是aspect框架中的注解
 *  作用：表示当前类是切面类
 *  切面类：是用来给业务方法增加功能的类，在这个类中有切面的功能代码
 *  位置：在类定义的上面
 *
 */
@Aspect
public class MyAspect {
    /*
    异常通知的定义格式：
    1.公共方法 public
    2.方法没有返回值
    3.方法名称可以自定义
    4.方法有一个参数Exception，如果还有是JoinPoint
     */
    /*
    @AfterThrowing:异常通知
        属性：1.value切入点表达式
            2.throwing 自定义的变量，表示目标方法抛出的异常对象，
                变量名必须和方法参数名一样
     特点：
        1.在目标方法抛出异常时才会执行
        2.可以做异常的监控，监控目标方法在执行时，是不是有异常
            如果有异常，可以发送邮件，短信通知

     执行过程：
        try{
            // 目标方法
        }catch(Exception e) {
            // 异常通知方法 @AfterThrowing
        }
     */
    @AfterThrowing(value = "execution(* *..SomeServiceImpl.doSecond(..))",throwing = "e")
    public void myAfterThrowing(Exception e) {
        System.out.println("异常通知，在方法发生异常时，通知" + e.getMessage());
        // 发送异常给专业人士查看（邮件，短信等）
    }
}
