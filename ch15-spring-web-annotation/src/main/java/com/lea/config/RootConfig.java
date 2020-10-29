package com.lea.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;

/**
 * @author lzc
 * @create 2020-10-29 15:32
 *
 * spring 根容器 不扫描controller
 */
@ComponentScan(value = "com.lea",excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Controller.class)
})
public class RootConfig {
}
