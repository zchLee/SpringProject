package org.example;

import org.example.aop01.SomeService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author lzc
 * @create 2020-10-5 12:51
 */
public class MyTest01 {

    @Test
    public void test() {
        String config= "applicationContext.xml";
        ApplicationContext context = new ClassPathXmlApplicationContext(config);
        // 从容器中获取目标对象
        SomeService proxy = context.getBean("someService", SomeService.class);
        // 获取的目标对象是代理对象 jdk动态代理
//        com.sun.proxy.$Proxy8
        System.out.println(proxy.getClass().getName());
        // 通过代理的对象执行方法，实现目标方法执行时，增强了功能
        proxy.doSome("lea", 23);
    }
}
