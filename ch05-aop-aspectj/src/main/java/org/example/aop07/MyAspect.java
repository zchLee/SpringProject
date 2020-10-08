package org.example.aop07;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @author lzc
 * @create 2020-10-5 12:29
 * @Aspect: 是aspect框架中的注解
 * 作用：表示当前类是切面类
 * 切面类：是用来给业务方法增加功能的类，在这个类中有切面的功能代码
 * 位置：在类定义的上面
 */
@Aspect
public class MyAspect {

    /*
    Pointcut:定义和管理切入点，如果你的项目中有多个切入点表达式是重复的，可以复用的。
        可以使用@Pointcut
       属性：value切入点表达式
       位置：在自定义方法上面
    特点：
        当使用@Pointcut定义在一个方法上时，此方法的名称就是切入点表达式的别名。
        在其他的通知中，value属性可以直接使用此方法的名称，代替切入点表达式
     */
    @Pointcut("execution(* *..SomeServiceImpl.doThird(..))")
    private void pc() {}

    @After("pc()")
    public void myAfter() {
        System.out.println("执行最终通知，总是会执行");
        // 一般做资源关闭工作的
    }

    @Before("pc()")
    public void myBefor() {
        System.out.println("执行前置通知，总是会执行");
        // 一般做资源关闭工作的
    }
}
