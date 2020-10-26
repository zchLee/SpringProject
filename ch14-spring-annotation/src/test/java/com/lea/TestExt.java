package com.lea;

import com.lea.ext.ExtConfig;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author lzc
 * @create 2020-10-26 17:57
 */
public class TestExt {

    @Test
    public void test() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ExtConfig.class);
        ac.close();
    }
}
