package com.lea;

import com.lea.di01.Student;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author lzc
 * @create 2020.09.29 17:58
 */
public class TestSpring01 {

    @Test
    public void test01() {
        String config = "applicationContext.xml";
        // ApplicationContext.xml 默认加载配置文件
//        ApplicationContext context = new ClassPathXmlApplicationContext(); // 用此构造器加载默认配置文件
        ApplicationContext context = new ClassPathXmlApplicationContext(config);
        // 从容器中获取对象
        Student myStudent = context.getBean("student", Student.class);
        System.out.println(myStudent);
    }

    /*
     @Scope：改变bean声明周期 默认singleton，同xml配置中的解释说明
 *
 *  下面两个注解只能修饰方法
 *  @PostConstruct: 指定bean初始方法
 *  @@PreDestroy： 做定bean销毁方法
     */
    @Test
    public void test0Other() {
        String config = "applicationContext.xml";
        // ApplicationContext.xml 默认加载配置文件
//        ApplicationContext context = new ClassPathXmlApplicationContext(); // 用此构造器加载默认配置文件
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(config);
        // 从容器中获取对象
        Student student = context.getBean("student", Student.class);
        Student myStudent = context.getBean("student", Student.class);
        System.out.println(myStudent == student);
        // 销毁方法 需要手动关闭spring容器
        context.close();
    }
}
