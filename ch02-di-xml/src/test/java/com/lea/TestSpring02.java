package com.lea;

import com.lea.di02.Student;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;


/**
 * @author lzc
 * @create 2020.09.29 11:49
 */
public class TestSpring02 {

    /*
    测试spring的使用
        spring默认创建对象时间：在创建spring容器（ApplicationContext）会创建配置文件中所有对象
     */
    @Test
    public void test() {
        // 1.指定spring配置文件的名称
        String config = "di02/applicationContext.xml";
        ApplicationContext context = new ClassPathXmlApplicationContext(config);

        // 从容器中获取某个对象
        Student student = context.getBean("student", Student.class);
        System.out.println(student);
    }
}
