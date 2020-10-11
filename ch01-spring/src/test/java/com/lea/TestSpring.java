package com.lea;

import com.lea.service.SomeService;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.util.Arrays;
import java.util.Date;


/**
 * @author lzc
 * @create 2020.09.29 11:49
 */
public class TestSpring {

    /*
    测试spring的使用
        spring默认创建对象时间：在创建spring容器（ApplicationContext）会创建配置文件中所有对象

     核心容器两个接口引发的问题：
        ApplicationContext：在构建核心容器时，创建对象的策略是采用立即加载的方式。读配置文件后，立马创建配置文件
        BeanFactory：在构建核心spring容器时，采取创建策略时采用延迟加载方式，什么时候根据id获取对象时才创建对象
     */
    @Test
    public void test() {
        // 1.指定spring配置文件的名称
        String config = "beans.xml";
        // 2.创建表示Spring容器的对象，ApplicationContext
        //      其表示Spring容器，通过容器对象就可以获取对应对象并使用对象
//        ClassPathXmlApplicationContext 表示从类路径中加载spring配置文件
//        FileSystemXmlApplicationContext 读取磁盘任意路径的配置文件用（需要有访问权限）
//        AnnotationConfigApplicationContext：用于读取注解创建容器
        ApplicationContext context = new ClassPathXmlApplicationContext(config);

        // 3. 从容器中获取某个对象 三种创建bean的方式
//        SomeService someService = context.getBean("someService", SomeService.class);
//        someService.doSome();
//        SomeService someServiceByFactory = context.getBean("someServiceByFactory", SomeService.class);
//        System.out.println(someServiceByFactory);
        SomeService staticSomeService = context.getBean("staticSomeService", SomeService.class);
        System.out.println(staticSomeService);
    }

    /*
    获取spring容器中 java对象的信息
     */
    @Test
    public void testSpringFiled() {
        String config = "beans.xml";
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(config);
        // 使用spring提供的方法，
//       1. 获取容器中定义对象的数量
        int count = context.getBeanDefinitionCount();
        System.out.println("容器中对象的数量" + count);
        // 2.容器中每个定义的对象名称
        String[] names = context.getBeanDefinitionNames();
        System.out.println(Arrays.toString(names));
    }


    // BeanFactory：在构建核心spring容器时，采取创建策略时采用延迟加载方式，什么时候根据id获取对象时才创建对象
    @Test
    public void testBeanFactory() {
        String config = "beans.xml";
        Resource rs = new ClassPathResource(config);
        BeanFactory factory = new XmlBeanFactory(rs);
        SomeService someService = factory.getBean("someService", SomeService.class);
        System.out.println(someService);
    }

    // bean的作用域
    @Test
    public void testScope() {
        String config = "beans.xml";
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(config);
        SomeService someService = context.getBean("someServiceByScope", SomeService.class);
        SomeService someService2 = context.getBean("someServiceByScope", SomeService.class);
        System.out.println("两次获取bean是否相等" + (someService == someService2));
        // 手动关闭容器
        context.close();
    }



    /*
    测试非自定义的类对象 交给spring管理
    */
    @Test
    public void testUsedClass() throws InterruptedException {
        String config = "beans.xml";
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(config);
        // 停顿一分钟 Tue Sep 29 14:33:30 CST 2020 打印时间还是创建实例的对象，
        // 再次印证了 ClassPathXmlApplicationContext加载配置文件就创建了对象的论点
        Thread.sleep(60000);
        Date myDate = context.getBean("myDate", Date.class);
        System.out.println(myDate);
    }
}
