package com.lea.bean;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * @author lzc
 * @create 2020-10-21 14:35
 */
@Component
public class Cat implements InitializingBean, DisposableBean {
    @Override
    public void destroy() throws Exception {
        System.out.println("Bean 销毁");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Bean 初始化");
    }
}
