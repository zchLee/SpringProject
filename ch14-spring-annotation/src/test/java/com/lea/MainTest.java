package com.lea;

import com.lea.bean.User;
import com.lea.conf.MainConfig;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

/**
 * @author lzc
 * @create 2020-10-20 17:40
 */
public class MainTest {

    @Test
    public void test1() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(MainConfig.class);
//        User bean = ac.getBean(User.class);
//        System.out.println(bean);

        String[] names = ac.getBeanDefinitionNames();
        Arrays.asList(names).forEach(System.out::println);
    }


    @Test
    public void testScope() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(MainConfig.class);
        User bean = ac.getBean(User.class);
        User bean2 = ac.getBean(User.class);
        System.out.println(bean);

        String[] names = ac.getBeanDefinitionNames();
        Arrays.asList(names).forEach(System.out::println);
    }

    @Test
    public void testConfiguration() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(MainConfig.class);
        String property = ac.getEnvironment().getProperty("os.name");
        System.out.println(property);
    }

    @Test
    public void testFactoryBean() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(MainConfig.class);
        // 调用getObject方法创建爱你对象
        Object colorFactoryBean = ac.getBean("colorFactoryBean");
        System.out.println(colorFactoryBean.getClass());

        // 获得工程Bean本身
        Object factoryBean = ac.getBean("&colorFactoryBean");
        System.out.println(factoryBean.getClass());
    }
}
