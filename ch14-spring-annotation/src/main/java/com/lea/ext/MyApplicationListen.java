package com.lea.ext;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author lzc
 * @create 2020-10-27 15:23
 *  创建监听事件
 */
@Component
public class MyApplicationListen implements ApplicationListener<ApplicationEvent> {
    // 当容器中发布此事件之后，方法触发
    @Override
    public void onApplicationEvent(ApplicationEvent event) {
            System.out.println("收到事件： " + event);
    }
}
