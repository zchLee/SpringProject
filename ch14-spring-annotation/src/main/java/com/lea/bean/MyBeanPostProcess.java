package com.lea.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @author lzc
 * @create 2020-10-21 14:47
 *
 * 后置处理器 ： 初始化前后方法调用  在spring中作用很多
 */
//@Component
public class MyBeanPostProcess implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("Bean后置处理器,在初始化方法之前" + beanName + bean.getClass());
        return null;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("Bean后置处理器,在初始化方法之后" + beanName + bean.getClass());
        return null;
    }
}
