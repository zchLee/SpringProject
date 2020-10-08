package org.example;

import org.example.aop08.SomeServiceImpl;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author lzc
 * @create 2020-10-5 12:51
 */
public class MyTest08 {

    @Test
    public void test() {
        String config= "applicationContext.xml";
        ApplicationContext context = new ClassPathXmlApplicationContext(config);
        // 从容器中获取目标对象
        SomeServiceImpl proxy = context.getBean("someService", SomeServiceImpl.class);
        /*
        目标类没有实现接口，就是使用的CGLIB动态代理，spring会自动应用CGLIB
         */
        System.out.println(proxy.getClass().getName());
        // 通过代理的对象执行方法，实现目标方法执行时，增强了功能
        proxy.doThird();
    }
}
