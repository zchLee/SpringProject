package org.example.aop03;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.example.aop02.Student;

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
    环绕通知方法：
        1.公共方法 public
        2.必须有一个返回值，推荐使用Object
        3.方法名称可以自定义
        4.方法有参数, 是一个固定的参数ProceedingJoinPoint
     */
    /**
     * @Around: 环绕通知
     *   属性：value切入点表达式
     *   位置：在方法定义的上面
     * 特点：
     *  1.它是功能最强的通知
     *  2.在目标方法的执行前、执行后都能增强功能。
     *  3.能控制目标方法是否被调用执行
     *  4.修改原来的目标方法的执行结果。影响最后的调用结果
     *
     *  环绕通知,类似JDK的动态代理的InvocationHandler接口
     *  参数：ProceedingJoinPoint等同于Method
     *      作用：执行目标方法
     *  返回值：目标方法执行的结果，可以被环绕通知方法修改
     *
     *  环绕通知：经常做事务，在目标方法开启之前，执行目标方法，在目标方法之后，提交事务
     */
    @Around(value = "execution(* *..SomeServiceImpl.doFirst(..))")
    public Object myAround(ProceedingJoinPoint pjp) throws Throwable {
        String name = "";
        Object[] args = pjp.getArgs();
        if (null != args && args.length > 0) {
            name = (String) args[0];
        }
        // 实现环绕功能的通知
        Object res = null;
//        2. 在目标方法之前或之后加入功能
        System.out.println("开始执行时的时间：" + new Date());
        //        1. 实现目标方法调用
        if ("lea".equals(name)) {
            res = pjp.proceed();  // method.invoke();
            System.out.println("环绕通知：在目标方法之后，提交事务");
        }
//        3. 修改目标的结果，影响方法最后的调用
        if (null != res) {
            res = "Hello Java";
        }
        // 返回目标方法执行结果
        return res;
    }
}
