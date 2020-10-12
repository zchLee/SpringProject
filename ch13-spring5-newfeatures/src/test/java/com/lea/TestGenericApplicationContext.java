package com.lea;

import com.lea.genericapplicationcontext.Some;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

/**
 * @author lzc
 * @create 2020-10-12 17:35
 */
public class TestGenericApplicationContext {

    // 以往是从配置文件中加载 启动spring容器的， ioc
    // 来个新的函数式风格创建对象，交给spring进行管理
    @Test
    public void test() {
        // 1. 创建GenericApplicationContext对象
        GenericApplicationContext context = new GenericApplicationContext();
        context.refresh(); // 刷新\启动
        // 2. 将对象注册到spring容器
//        context.registerBean(Some.class, () -> new Some());
        // 获取对象，此种方法注入bean需要用类的全路径来获取对象
//        Class<Some> clazz = Some.class;
//        Some bean = context.getBean(clazz.getName(), clazz);

        context.registerBean("some", Some.class, () -> new Some()); // 指定bean名字
        Some bean = context.getBean("some", Some.class);
        System.out.println(bean);

    }
}
