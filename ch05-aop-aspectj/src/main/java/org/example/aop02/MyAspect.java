package org.example.aop02;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
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
    后置通知方法：
        1.公共方法 public
        2.方法没有返回值
        3.方法名称可以自定义
        4.方法有参数, 推荐使用Object，参数名称自定义
    @AfterReturning 表示后置通知注解
        属性 ：
            1、value：是切入点表达式，表示切面的功能执行的位置。
            2、returning :自定义的变量，表示目标方法的返回值的。
                自定义的变量名必须和通知方法的形参名一样
        位置：在方法上
      特点：
        1.在目标方法执行之后先执行
        2.不会改变目标方法的返回值，可以根据这个返回值做不同的处理功能
        3.可以修改这个返回值
        4.方法有参数, 推荐使用Object，参数名称自定义
     */
    @AfterReturning(value = "execution(* *..SomeServiceImpl.doOther(..))",returning = "res")
    public void myAfter(JoinPoint jp, Object res) {
        System.out.println("后置通知方法： " + jp.getSignature());
        // res是目标方法的返回值，根据返回值做对应处理
        System.out.println((String) res);
        // 修改返回值，会影响调用返回结果的，此处String不可变
        res = "AOP修改了结果";
    }

    @AfterReturning(value = "execution(* *..SomeServiceImpl.doStu(..))",returning = "res")
    public void myAfterStu(Object res) {
        Student stu = (Student) res;
        // res是目标方法的返回值，根据返回值做对应处理
        System.out.println("为改变的值：" + stu);
        // 修改返回值，会影响调用返回结果的，
        stu.setName("黄鹤");
    }
}
