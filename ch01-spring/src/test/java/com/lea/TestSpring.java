package com.lea;

import com.lea.service.SomeService;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

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
     */
    @Test
    public void test() {
        // 1.指定spring配置文件的名称
        String config = "beans.xml";
        // 2.创建表示Spring容器的对象，ApplicationContext
        //      其表示Spring容器，通过容器对象就可以获取对应对象并使用对象
//        FileSystemXmlApplicationContext 读取项目外的配置文件用
//        ClassPathXmlApplicationContext 表示从类路径中加载spring配置文件
        ApplicationContext context = new ClassPathXmlApplicationContext(config);

        // 3. 从容器中获取某个对象
        SomeService someService = context.getBean("someService", SomeService.class);
//        System.out.println(someService);
        someService.doSome();
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
