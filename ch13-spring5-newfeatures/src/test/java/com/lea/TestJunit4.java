package com.lea;

import com.lea.genericapplicationcontext.Some;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author lzc
 * @create 2020-10-12 17:51
 *
 * Junit4整合spring-test 要配置4.12及以上的junit版本
 */
@RunWith(SpringJUnit4ClassRunner.class)  // 指定单元测试版本
@ContextConfiguration("classpath:bean.xml") // 加载配置文件
public class TestJunit4 {
    @Autowired
    private App app;

    @Test
    public void test() {
        System.out.println(app);
    }
}
