package org.example.aop01;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

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
    定义方法：方法是实现切面功能的
    方法定义的要求：
    1.公共方法 public
    2.方法没有返回值
    3.方法名称可以自定义
    4.方法可以有参数，也可以没有。
        参数不是自定义的，有几个类型可以使用
     */
    /*
    @Before 表示前置通知注解
        属性 value：是切入点表达式，表示切面的功能执行的位置。
        位置：在方法上
      特点：
        1.在目标方法执行之前先执行
        2.不会改变目标方法的执行结果
        3.不会影响目标方法的执行。
     */
//    @Before(value = "execution(public void org.example.aop01.SomeServiceImpl.doSome(String,Integer))")
//    public void myBefore() {
//        // 切面要执行的功能代码
//        System.out.println("前置切面通知：" + new Date());
//    }

    @Pointcut("execution(public void org.example.aop01.SomeServiceImpl.doSome(String,Integer))")
    public void pt1() {}

    /*
    指定通知方法中的参数：org.aspectj.lang.JoinPoint
     joinPoint:业务方法，要加入切面功能的业务方法
     作用：可以在通知方法中获取方法执行的信息，例如方法名，方法参数
     如果切面功能需要用到方法的信息，就加入joinPoint

     这个joinPoint参数的值是由框架赋予，必须是第一个位置的参数
     */
    @Before(value = "pt1()")
    public void myBefore(JoinPoint joinPoint) {
        System.out.println("方法的完整定义：" + joinPoint.getSignature());
        System.out.println("方法的名称：" + joinPoint.getSignature().getName());

        // 输出目标方法在执行时的参数
        System.out.println("方法的所有参数");
        Arrays.asList(joinPoint.getArgs()).forEach(System.out::print);
        System.out.println();
        // 切面要执行的功能代码
        System.out.println("前置切面通知：" + new Date());
    }
}
