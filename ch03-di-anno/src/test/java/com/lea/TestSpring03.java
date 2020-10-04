package com.lea;

import com.lea.di03.Student;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author lzc
 * @create 2020.09.29 17:58
 */
public class TestSpring03 {

    @Test
    public void test01() {
        String config = "applicationContext.xml";
        // ApplicationContext.xml 默认加载配置文件
//        ApplicationContext context = new ClassPathXmlApplicationContext(); // 用此构造器加载默认配置文件
        ApplicationContext context = new ClassPathXmlApplicationContext(config);
        // 从容器中获取对象
        Student myStudent = context.getBean("myStudent", Student.class);
        System.out.println(myStudent);
    }
}
