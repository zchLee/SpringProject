package com.lea.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @author lzc
 * @create 2020-10-22 13:42
 *
 *  JoinPoint joinPoint： 切面传递参数的封装类, 位置必须在第一个
 */
@Aspect // 切面类
public class LogAspects {

    /* 抽取公共的切入点表达式 (可以省略 execution())
        1. 本类引用  ---> 方法名()
     */
    @Pointcut("execution(public int MathCalculator.div(int ,int))")
    public void ex() {}

    // @Before方法执行前，执行 value: 切入点表达式（指定在哪个方法切入）
    @Before("execution(public int com.lea.aop.MathCalculator.div(int ,int))")
    public void logStart(JoinPoint joinPoint) {
        System.out.println(joinPoint.getSignature().getName() + "除法运行之前， 从参数列表是{" + joinPoint.getArgs() + "}");
    }
    // @Before方法执行前，执行 value: 切入点表达式（指定在哪个方法切入）
    @After("ex()")
    public void logEnd(JoinPoint joinPoint) {
        System.out.println(joinPoint.getSignature().getName() + "除法运行结束");
    }

    // AfterReturning 特有属性 returning 获取返回值，value与参数名要一致
    @AfterReturning(value = "ex()", returning = "result")
    public void logReturn(Object result) {
        System.out.println("除法正常返回， 运行结果时{" + result + "}");
    }

    // AfterThrowing 特有属性 throwing 获取抛出异常 value值要与参数名一致
    @AfterThrowing(value = "ex()", throwing = "e")
    public void logException(JoinPoint joinPoint, Exception e) {
        System.out.println(joinPoint.getSignature().getName() + "运行异常， 异常信息{" + e + "}");
    }
}
