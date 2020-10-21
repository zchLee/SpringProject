package com.lea.bean;

import org.springframework.beans.factory.FactoryBean;

/**
 * @author lzc
 * @create 2020-10-21 13:44
 * 通过实现FactoryBean接口 注册bean
 */
public class ColorFactoryBean implements FactoryBean<YellowByFactoryBean> {

    // 创建对象，就会返回到spring容器中
    @Override
    public YellowByFactoryBean getObject() throws Exception {
        System.out.println("创建对象");
        return new YellowByFactoryBean();
    }

    // 通过Bean工厂创建的类型
    @Override
    public Class<?> getObjectType() {
        System.out.println("规范类型");
        return YellowByFactoryBean.class;
    }

    // 控制是否是单例， true：单实例， false：多实例
    @Override
    public boolean isSingleton() {
        return true;
    }
}
