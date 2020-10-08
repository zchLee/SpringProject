package org.example;

import org.example.aop02.SomeService;
import org.example.aop02.Student;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author lzc
 * @create 2020-10-5 12:51
 */
public class MyTest02 {

    @Test
    public void test() {
        String config= "applicationContext.xml";
        ApplicationContext context = new ClassPathXmlApplicationContext(config);
        // 从容器中获取目标对象
        SomeService proxy = context.getBean("someService", SomeService.class);
        System.out.println(proxy.getClass().getName());
        // 通过代理的对象执行方法，实现目标方法执行时，增强了功能
//        String doOther = proxy.doOther("lea", 23);
//        System.out.println(doOther);
        Student stu = proxy.doStu("lea", 23);
        System.out.println("最终结果" + stu);
    }
}
