package com.lea.conf;

import com.lea.bean.Car;
import com.lea.dao.UserDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * @author lzc
 * @create 2020-10-21 16:33
 *
 * 自动装配：
 *  spring利用依赖注入（DI）：完成对IOC容器中各个组件的依赖关系赋值
 *     1.Autowired------------------------------》 spring定义
 *       1.1 默认按照类型从容器中找对应的组件，如果有多个，按照属性名称去容器中找
 *       1.2 @Qualifie指定要装配的Bean id，而不是属性名
 *       1.3 @@Primary 指定首选自动装配，也可以继续使用@Qualifie指定装配的bean名字
 *     2.@Resource(JSR250) 和 @Inject(JSR330)  ---- > java规范的注解
 *       2.1 Resource:
 *          默认按照名称默认装配，可以指定name属性 指定id 装配。 没有支持@primary功能 没有支持@Autowired(required =false)
 *       2.2 Inject:
 *          需要对应的Inject依赖，支持Autowired功能
 *
 * AutowiredAnnotationBeanPostProcessor：解析完成自动装配功能
 *
 * 自定义组件想要使用spring容器底层的一些组件（ApplicationContext， BeanFactory， xx）
 *      自定义类实现xxxAware: 在创建对象的时候，会调用接口规定的方法，注入相关的组件 Aware
 *      把spring底层一些组件注入到自定义的Bean中
 *          xxxAware:使用xxxAwareProcessor实现注入
 *              ApplicationContextAware==》ApplicationContextAwareProcessor
 */
@Configuration
@ComponentScan("com.lea")
public class MainConfigOfAutowired {

    @Primary // 告诉spring容器首选
    @Bean("userDao2")
    public UserDao userDao2() {
        return new UserDao();
    }

    /*
    Bean标注的方法创建对象时，方法参数的值从容器中获取
     */
    @Bean
    public Car car(UserDao dao) {
        System.out.println(dao);
        return new Car();
    }
}
