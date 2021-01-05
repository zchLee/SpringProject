package com.lea.circulardependency;

import org.springframework.beans.factory.aspectj.AnnotationBeanConfigurerAspect;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author lzc
 * @create 2021-1-4 17:00
 */
public class TestMain {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(Config.class);
        Object a = ac.getBean("a");
        Object b = ac.getBean("b");
        System.out.println(a + "\t" + b);
    }
}
