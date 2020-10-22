package com.lea.awre;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.stereotype.Component;
import org.springframework.util.StringValueResolver;

/**
 * @author lzc
 * @create 2020-10-21 17:17
 *
 *  从spring中拿到spring底层的组件
 */
@Component
public class MyAware implements ApplicationContextAware, BeanNameAware, EmbeddedValueResolverAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("传入的IOC：" + applicationContext);
        this.applicationContext = applicationContext;
    }
//    ApplicationContextAware
    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

//    BeanNameAware
    @Override
    public void setBeanName(String name) {
        System.out.println("当前bean的名字" + name);
    }

//    EmbeddedValueResolverAware 传入字符串解析器
    @Override
    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        String s = resolver.resolveStringValue("你好${os.name} 我是#{20 * 8}");
        System.out.println(s);
    }
}
