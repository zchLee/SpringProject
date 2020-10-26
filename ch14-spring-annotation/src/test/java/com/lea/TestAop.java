package com.lea;

import com.lea.aop.MathCalculator;
import com.lea.conf.MainConfigOfAOP;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author lzc
 * @create 2020-10-22 14:05
 */
public class TestAop {

    @Test
    public void test() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(MainConfigOfAOP.class);
        MathCalculator bean = ac.getBean(MathCalculator.class);
        bean.div(1, 2);
        System.out.println(" end ");
    }
}
