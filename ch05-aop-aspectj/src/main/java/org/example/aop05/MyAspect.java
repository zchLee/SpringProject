package org.example.aop05;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;

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
    最终通知的定义格式：
    1.公共方法 public
    2.方法没有返回值
    3.方法名称可以自定义
    4.方法没有参数，如果还有是JoinPoint

    @After:最终通知
        属性：value 切入点表达式
        使用位置：在方法上
    特点：
        1.总是会执行
        2.在目标方法执行之后执行

    try{
    }catch(Exception e) {
    }finally{
        // 执行最终通知方法
    }
     */
    @After("execution(* *..SomeServiceImpl.doThird(..))")
   public void myAfter() {
        System.out.println("执行最终通知，总是会执行");
        // 一般做资源关闭工作的
   }
}
