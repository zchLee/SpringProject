package com.lea.conf;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.util.StringValueResolver;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

/**
 * @author lzc
 * @create 2020-10-22 10:24
 *
 * @Profile： 指定组件在哪个环境下的情况下才能被注册到容器中，没有标注，就是任何环境下都可以注册到容器中, 默认是default环境
 *      1.加了此注解的Bean只有spring 激活时才会被注册到容器中，
 *      spring为我们提供的可以根据当前环境，动态激活和切换一系列组件的功能；
 *      2. 写在配置类上，只有指定的环境时，整个配置类的所有配置才能生效
 *      3. 没有标注环境标识的bean，在任何环境下都是加载的
 *
 */
//@Configuration
//@PropertySource("classpath:dbconfig.properties")
public class MainConfigOfProfile implements EmbeddedValueResolverAware {

    // EmbeddedValueResolverAware 值解析器
    private StringValueResolver stringValueResolver;  // 还有一种方法 实现EnvironmentAware，从运行时环境中拿

    @Value("${db.user}")
    private String user;

//    private String password;

    private String driverClass;

    @Profile("dev")
    @Bean("devDataSource")
    public DataSource dataSourceDev(@Value("${db.password}") String password) throws PropertyVetoException {
//        this.password = password;
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setUser(user);
        dataSource.setPassword(password);
        dataSource.setDriverClass(driverClass);
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/test");
        return dataSource;
    }

    @Profile("prod")
    @Bean("prodDataSource")
    public DataSource dataSourceProd(@Value("${db.password}") String password) throws PropertyVetoException {

        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setUser(user);
        dataSource.setPassword(password);
        dataSource.setDriverClass(driverClass);
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/world");
        return dataSource;
    }

    @Override
    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        this.stringValueResolver = resolver;
        driverClass = stringValueResolver.resolveStringValue("${db.driverClass}");
    }
}
