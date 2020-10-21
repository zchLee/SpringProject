package com.lea.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @author lzc
 * @create 2020-10-21 11:07
 */
public class ZHangSanCondition implements Condition {

    /**
     * @param context       上下文
     * @param metadata      注释信息
     * @return: boolean
     */
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        // windows系统就给张三
        Environment environment = context.getEnvironment();  // 环境
        String property = environment.getProperty("os.name");
        if (property.contains("windows")) {
            return true;
        }
        return false;
    }
}
