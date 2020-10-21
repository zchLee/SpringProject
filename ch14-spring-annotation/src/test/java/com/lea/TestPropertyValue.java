package com.lea;

import com.lea.conf.MainConfigOfPropertyValues;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author lzc
 * @create 2020-10-21 16:02
 */
public class TestPropertyValue {

    @Test
    public void test() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(MainConfigOfPropertyValues.class);
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        Object person = ac.getBean("person");
        System.out.println(person);
    }
}
