package com.lea.ext;

import com.lea.bean.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author lzc
 * @create 2020-10-26 17:30
 * 扩展原理
 *  BeanPostProcessor; bean后置处理器，bean创建对象 初始化前后进行拦截工作的
 *  BeanFactoryPostProcessor； beanFactory后置处理器；
 *      在beanFactory标准初始化之后调用；所有的bean定义已经保存加载到beanFactory，但是bean的实例还未创建
 * 1）、ioc容器创建对象
 * 2）、invokeBeanFactoryPostProcessors(beanFactory); 执行BeanFactoryPostProcessors；
 *      如何找到所有的BeanFactoryPostProcessors并执行他们的方法；
 *          1）、直接在BeanFactory中找到所有类型是BeanFactoryPostProcessors的组件，并执行他们的方法
 *          2）、在初始化创建其他组件前执行
 *
 */
@Configuration
@ComponentScan("com.lea.ext")
public class ExtConfig {

    @Bean
    public Person person() {
        return new Person();
    }
}
