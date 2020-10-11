package com.lea.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * @author lzc
 * @create 2020-10-11 15:12
 *
 * @Configuration: 指定当前类是一个配置类
 *       细节：当配置类作为AnnotationContextConfigApplicationContext对象创建参数时，该注解可以不写，其会自动注入spring容器中
 * @ComponentScan：用于通过注解扫描spring在创建容器时要扫描的包
 *      属性：value=basePackage 都是用于指定创建容器时，要扫描的包路径
 *          等同于在xml中配置了 包扫描 标签 <context:component-scan base-package="com.lea.di01"/>
 * @Bean: 把当前方法的返回值作为bean对象存入spring的ioc容器中
 *      属性：name指定bean的id，不写默认当前方法的名称
 *      细节：
 *          当我们使用注解配置方法时;r如果方法有参数。spring框架会去容器中检查是否有可用bean对象
 *          查找方式和Autowired方式一样
 *
 * @Import: 导入其他的配置类
 *         value：指定其他配置类的字节码，当使用import注解后，有Import注解的类就是主配置类，value值都是字配置类
 */
@Configuration
//@Import(JDBCConfig.class)
@PropertySource(name = "properties", value = "classpath:jdbc.properties")
public class SpringConfiguration {

//    @Autowired
//    JDBCProperties jdbcProperties;

    @Bean
    public DataSource dataSource(ApplicationContext context) throws Exception {
        // 方式一：
//        InputStream is = SpringConfiguration.class.getClassLoader().getResourceAsStream("jdbc.properties");
//        Properties properties = new Properties();
//        properties.load(is);
//        return DruidDataSourceFactory.createDataSource(properties);
        // 方式二：
        // 此处： properties加载了，但是Value注入失败，故手动拿值.
        // 如果看官有解决方法，烦请告知
        StandardEnvironment environment = (StandardEnvironment) context.getEnvironment();
        org.springframework.core.env.PropertySource<?> properties = environment.getPropertySources().get("properties");
//        org.springframework.core.env.PropertySource<?> properties = context.getEnvironment().getPropertySources().get("properties");
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUrl(properties.getProperty("url").toString());
        druidDataSource.setUsername(properties.getProperty("username").toString());
        druidDataSource.setPassword(properties.getProperty("password").toString());
        druidDataSource.setMaxActive(Integer.parseInt(properties.getProperty("maxActive").toString()));
        druidDataSource.init();
        return druidDataSource;
    }

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactoryBean sqlSessionFactory(DataSource dataSource)  {
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dataSource);
//        mybatis主配置文件的位置
//        configLocation属性是Resource类型，读取配置文件
//        它的赋值，使用value
//        在spring中要指定其它配置文件位置要用classpath：
        Resource resource = new ClassPathResource("mybatis.xml");
        sqlSessionFactory.setConfigLocation(resource);
        return sqlSessionFactory;
    }

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer sc = new MapperScannerConfigurer();
//        指定包名，包名是dao接口所在的包名
//        MapperScannerConfigurer会扫描这个包中的所有接口，
//        把每个接口都执行一次getMapper(interface.class)， 得到每个接口的dao对象
//        创建好的dao对象，放进spring的ioc容器中.dao对象的默认名称是 接口名首字母小写
//        多个包用逗号分隔
        sc.setBasePackage("com.lea.dao");
//        指定sqlSessionFactory对象的id
        sc.setSqlSessionFactoryBeanName("sqlSessionFactory");
        return sc;
    }
}
