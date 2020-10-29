1.web容器在启动的时候，会扫描每个jar包下的META-INF/service/javax.servlet.ServletContainerInitializer
2.加载这个文件的类 org.springframework.web.SpringServletContainerInitializer
3.spring启动会加载感兴趣的 WebApplicationInitializer 接口下的所有组件
4.并且为 WebApplicationInitializer 组件创建对象（组件不是接口，不是抽象类）
    1）、AbstractContextLoaderInitializer：  
        创建根容器 RootApplicationContext：createRootApplicationContext();     
    2）、AbstractDispatcherServletInitializer
        创建web容器：createServletApplicationContext();  
        创建DispatcherServlet：createDispatcherServlet(servletAppContext);    
        添加映射：registration.addMapping(getServletMappings());  
    3)、AbstractAnnotationConfigDispatcherServletInitializer     注解方式配置的DispatcherServlet初始化器
        创建根容器：createRootApplicationContext()     
        获取一个配置类： getRootConfigClasses();       
        创建web的ioc容器：createServletApplicationContext()
            获取配置类：getServletConfigClasses();
            
总结：
    已注解方式来启动SpringMVC，继承 AbstractAnnotationConfigDispatcherServletInitializer
    指定配置信息，实现DispatcherServlet的配置信息
===================================
定制SpringMVC
1.@EnableWebMvc：开启SpringMVC定制配置功能 类似 <mvc:annotation-driver/>; 配置标签
2.配置组件（视图解析器，视图映射，静态资源映射，拦截器）
    jdk8 implements WebMvcConfigurer
    jdk8以前 extends WebMvcConfigurerAdapter