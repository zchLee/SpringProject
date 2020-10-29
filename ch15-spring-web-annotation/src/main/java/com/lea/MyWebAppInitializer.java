package com.lea;

import com.lea.config.AppConfig;
import com.lea.config.RootConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * @author lzc
 * @create 2020-10-29 15:26
 */
public class MyWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    // 获取根容器的配置类（spring的配置文件）
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] {RootConfig.class};
    }

    // web容器的配置类（springMVC的配置文件） 子容器
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] {AppConfig.class};
    }

    // 获取DispatcherServlet的映射信息
    // jsp是tomcat的引擎解析的
    @Override
    protected String[] getServletMappings() {
        // 拦截所有请求，包括静态资源  不包括 *.jsp
        return new String[] {"/"};
    }
}
