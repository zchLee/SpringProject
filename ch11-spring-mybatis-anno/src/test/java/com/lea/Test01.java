package com.lea;

import com.lea.config.ComScan;
import com.lea.config.SpringConfiguration;
import com.lea.domain.Student;
import com.lea.service.StudentService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;

import java.util.Arrays;
import java.util.List;

/**
 * @author lzc
 * @create 2020-10-9 14:04
 */
public class Test01 {

    @Test
    public void test() {
        ApplicationContext context = new AnnotationConfigApplicationContext(ComScan.class);
        System.out.println("容器中对象的名称");
        Arrays.asList(context.getBeanDefinitionNames()).forEach(name -> System.out.println(name + " || " + context.getBean(name).getClass().getName()));
        System.out.println("对象打印完毕");
    }

    @Test
    public void testMybatis() {
        ApplicationContext context = new AnnotationConfigApplicationContext(ComScan.class);
        StudentService studentService = context.getBean("studentServiceImpl", StudentService.class);
        List<Student> students = studentService.queryStudent();
        students.forEach(System.out::println);
    }

    @Test
    public void testProperties() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ComScan.class);
        MutablePropertySources propertySources = context.getEnvironment().getPropertySources();
        PropertySource<?> properties = propertySources.get("properties");
//        Object properties = context.getEnvironment().getPropertySources().get("properties");
        System.out.println(properties);
    }
}
