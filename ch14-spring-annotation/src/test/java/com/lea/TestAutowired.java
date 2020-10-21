package com.lea;

import com.lea.conf.MainConfig;
import com.lea.conf.MainConfigOfAutowired;
import com.lea.service.UserService;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author lzc
 * @create 2020-10-21 16:36
 */
public class TestAutowired {

    @Test
    public void test() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(MainConfigOfAutowired.class);
        UserService userService = ac.getBean("userService", UserService.class);
        userService.dao();
    }
}
