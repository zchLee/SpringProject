package org.example;

import org.example.aop05.SomeService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author lzc
 * @create 2020-10-5 12:51
 */
public class MyTest05 {

    @Test
    public void test() {
        String config= "applicationContext.xml";
        ApplicationContext context = new ClassPathXmlApplicationContext(config);
        // 从容器中获取目标对象
        SomeService proxy = context.getBean("someService", SomeService.class);
        System.out.println(proxy.getClass().getName());
        // 通过代理的对象执行方法，实现目标方法执行时，增强了功能
        proxy.doThird();
    }
}
