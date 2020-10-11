package com.lea;

import com.lea.config.ComScan;
import com.lea.domain.Student;
import com.lea.service.StudentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.PropertySource;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;

/**
 * @author lzc
 * @create 2020-10-9 14:04
 *
 * 使用JUnit单元测试。测试我们的配置
 * spring整合Junit配置
 *  1.导入spring整合junit的maven依赖 spring-test
 *  2.使用Spring整合Junit提供的注解 @RunWith
 *  3.告知Spring的运行器，spring和ioc创建基于xml还是注解，并说明位置
 *      @ContextConfiguration
 *          locations:指定xml文件位置，加上classpath关键字，表示在类路径下
 *          classes：指定注解类所在的位置
 *  当使用spring5.x版本时，要求junit的依赖必须是4.12及以上
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ComScan.class)
public class SpringTest {

    @Autowired
    private StudentService studentService;

    @Autowired
    private ApplicationContext context;

    @Test
    public void test() {
        System.out.println("容器中对象的名称");
        Arrays.asList(context.getBeanDefinitionNames()).forEach(name -> System.out.println(name + " || " + context.getBean(name).getClass().getName()));
        System.out.println("对象打印完毕");
    }

    @Test
    public void testMybatis() {
        List<Student> students = studentService.queryStudent();
        students.forEach(System.out::println);
    }

    @Test
    public void testProperties() {
        StandardEnvironment environment = (StandardEnvironment) context.getEnvironment();
        PropertySource<?> properties = environment.getPropertySources().get("properties");
//        Object properties = context.getEnvironment().getPropertySources().get("properties");
        System.out.println(properties);
    }
}
