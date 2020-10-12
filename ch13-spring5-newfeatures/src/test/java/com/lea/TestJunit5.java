package com.lea;






import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.Extensions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;


/**
 * @author lzc
 * @create 2020-10-12 17:51
 *
 * Junit5整合spring-test
 */
//@ExtendWith(SpringExtension.class)
//@ContextConfiguration("classpath:bean.xml")
    // 符合注解，代替上面两个
@SpringJUnitConfig(locations = "classpath:bean.xml")
public class TestJunit5 {
    @Autowired
    private App app;

    @Test
    public void test() {
        System.out.println(app);
    }
}
