package com.lea;

import com.lea.di05.Student;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * @author lzc
 * @create 2020.09.29 11:49
 */
public class TestSpring05 {

    /*
    测试spring的使用
        byType引用类型自动注入
     */
    @Test
    public void test() {
        // 1.指定spring配置文件的名称
        String config = "di05/applicationContext.xml";
        ApplicationContext context = new ClassPathXmlApplicationContext(config);

        // 从容器中获取某个对象
        Student student = context.getBean("student", Student.class);
        System.out.println(student);
    }
}
