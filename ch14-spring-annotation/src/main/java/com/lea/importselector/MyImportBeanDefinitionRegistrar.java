package com.lea.importselector;

import com.lea.bean.OtherImportBeanDefinitionRegistrar;
import com.lea.bean.OtherWhite;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author lzc
 * @create 2020-10-21 11:39
 */
public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

    /**
     * @param importingClassMetadata        当前类的注解信息
     * @param registry                      BeanDefinition注册类
     *                                         把所有需要注册添加到容器中的bean，通过registerBeanDefinition 方法注册进来
     * @param importBeanNameGenerator
     * @return: void
     */
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry, BeanNameGenerator importBeanNameGenerator) {
        boolean otherRed = registry.containsBeanDefinition("com.lea.bean.OtherRed");
        boolean otherWhite = registry.containsBeanDefinition("com.lea.bean.OtherWhite");
        if (otherWhite && otherRed) {
            // 自定义bean名。
            // 定义BeanDefinition( 注册的类信息)
            RootBeanDefinition beanDefinition = new RootBeanDefinition(OtherImportBeanDefinitionRegistrar.class);
            registry.registerBeanDefinition("custom", beanDefinition);
        }
    }
}
