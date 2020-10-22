package com.lea;

import com.lea.conf.MainConfigOfLifeCycle;
import com.lea.conf.MainConfigOfProfile;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.sql.DataSource;

/**
 * @author lzc
 * @create 2020-10-22 10:50
 */
public class TestProfile {

    /*
    如何指定环境启动spring？
        1. 使用命令行动态参数
            在虚拟机参数（VM options）位置加: -Dspring.profiles.active=dev
        2. 使用代码方式：

     */
    @Test
    public void test() {
        // 1. 创建ioc容器
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
        // 2. 设置激活环境
        ac.getEnvironment().setActiveProfiles("dev");
        // 3. 注册配置类
        ac.register(MainConfigOfProfile.class);
        // 4. 启动
        ac.refresh();

        String[] names = ac.getBeanNamesForType(DataSource.class);
        for (int i = 0; i < names.length; i++) {
            System.out.println(names[i]);
        }
        ac.close();

    }
}
