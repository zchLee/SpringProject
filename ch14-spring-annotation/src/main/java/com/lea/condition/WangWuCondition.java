package com.lea.condition;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @author lzc
 * @create 2020-10-21 11:08
 */
public class WangWuCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        // bean定义的注册类
        ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
        // 是否注册了user
        boolean user = beanFactory.containsBean("user");
        if (user)
            return true;
        return false;
    }
}
