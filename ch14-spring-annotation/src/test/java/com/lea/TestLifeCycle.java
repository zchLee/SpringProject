package com.lea;

import com.lea.conf.MainConfigOfLifeCycle;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author lzc
 * @create 2020-10-21 14:23
 */
public class TestLifeCycle {

    @Test
    public void test() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(MainConfigOfLifeCycle.class);
        Object car = ac.getBean("car");
        System.out.println(car);

        ac.close();

    }

    @Test
    public void test1() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(MainConfigOfLifeCycle.class);
        Object c = ac.getBean("cat");
        System.out.println(c);
    }
}
