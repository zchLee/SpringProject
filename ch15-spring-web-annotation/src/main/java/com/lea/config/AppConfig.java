package com.lea.config;

import com.lea.interceptor.MyInterceptors;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author lzc
 * @create 2020-10-29 15:32
 *
 * spring容器 只扫描controller
 *
 * WebMvcConfigurerAdapter弃用， jdk8有了新特性 default
 */
@ComponentScan(useDefaultFilters = false, value = "com.lea", includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Controller.class))
@EnableWebMvc
public class AppConfig implements WebMvcConfigurer {

    // 静态资源 配置
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable(); // 开启
    }

    // 配置视图解析器
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
//        registry.jsp();  // 默认从 WEB-INF 中找视图文件
        registry.jsp("/WEB-INF/views/", ".jsp");
    }

    // 配置拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // /** 拦截任意路径
        registry.addInterceptor(new MyInterceptors()).addPathPatterns("/**");
    }
}
