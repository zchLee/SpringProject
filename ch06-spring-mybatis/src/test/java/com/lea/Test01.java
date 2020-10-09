package com.lea;

import com.lea.dao.StudentDao;
import com.lea.domain.Student;
import com.lea.service.StudentService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;
import java.util.List;

/**
 * @author lzc
 * @create 2020-10-9 14:04
 */
public class Test01 {

    @Test
    public void test() {
        String config = "applicationContext.xml";
        ApplicationContext context = new ClassPathXmlApplicationContext(config);
        System.out.println("容器中对象的名称");
        Arrays.asList(context.getBeanDefinitionNames()).forEach(name -> System.out.println(name + " || " + context.getBean(name).getClass().getName()));
        System.out.println("对象打印完毕");
    }

    @Test
    public void test1() {
        String config = "applicationContext.xml";
        ApplicationContext context = new ClassPathXmlApplicationContext(config);
        StudentDao studentDao = (StudentDao) context.getBean("studentDao");
        studentDao.insertStudent(new Student(1, "刘德华", "liudehua@qq.com", 18));
//        List<Student> students = studentDao.selectStudents();
    }

    @Test
    public void testService() {
        String config = "applicationContext.xml";
        ApplicationContext context = new ClassPathXmlApplicationContext(config);
        StudentService studentService = context.getBean("studentService", StudentService.class);
        // spring整合mybatis时，如果为配置事务，它是自动提交的，无需执行SqlSession.commit()
        studentService.addStudent(new Student(4, "张学友", "liudehua@qq.com", 18));
    }

    @Test
    public void ServiceSelect() {
        String config = "applicationContext.xml";
        ApplicationContext context = new ClassPathXmlApplicationContext(config);

        StudentService studentService = context.getBean("studentService", StudentService.class);
        // spring整合mybatis时，如果为配置事务，它是自动提交的，无需执行SqlSession.commit()
        // 查询中文是乱码的，数据库的字符集是utf8mb4 解决方法: 在url配置后面加?useUnicode=true&characterEncoding=utf8
        List<Student> students = studentService.queryStudent();
        students.forEach(System.out::println);
    }
}
