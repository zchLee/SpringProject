package com.lea.ext;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * @author lzc
 * @create 2020-10-26 17:35
 */
//@Component
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        // 可以 ConfigurableListableBeanFactory 里的方法操作bean、beanFactory
        System.out.println("MyBeanFactoryPostProcessor.. postProcessBeanFactory 开始执行");
        System.out.println("当前BeanFactory中有" + beanFactory.getBeanDefinitionCount() + "个");
        for (String name : beanFactory.getBeanDefinitionNames()) {
            System.out.println("---------->" + name);
        }
    }
}
