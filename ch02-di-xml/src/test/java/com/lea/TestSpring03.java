package com.lea;

import com.lea.di03.Student;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;


/**
 * @author lzc
 * @create 2020.09.29 11:49
 */
public class TestSpring03 {

    /*
    测试spring的使用
        构造器注入
     */
    @Test
    public void test() {
        // 1.指定spring配置文件的名称
        String config = "di03/applicationContext.xml";
        ApplicationContext context = new ClassPathXmlApplicationContext(config);

        // 从容器中获取某个对象
        Student student = context.getBean("student", Student.class);
        System.out.println(student);
        Student studentByIndex = context.getBean("studentByIndex", Student.class);
        System.out.println(studentByIndex);

        File myFile = context.getBean("myFile", File.class);
        System.out.println(myFile.getName());
    }
}
