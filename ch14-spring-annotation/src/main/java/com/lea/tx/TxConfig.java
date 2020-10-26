package com.lea.tx;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

/**
 * @author lzc
 * @create 2020-10-26 14:46
 * 声明式事务
 *     环境搭建：
 *      1.导入相关依赖
 *          数据源、数据库驱动、spring-jdbc模块
 *      2.配置数据源、jdbcTemplate(Spring提供的简化数据库操作工具) 操作数据
 *      3. 给方法标注@Transactional 表示当前方法支持事务方法
 *      4. @EnableTransactionManagement 开启基于注解的事务管理功能
 *      5. 配置事务管理器 来控制事务
 *          @Bean
 *          public PlatformTransactionManager transactionManager(DataSource dataSource)
 *
 * 原理：
 * 1）、@EnableTransactionManagement
 *  利用 TransactionManagementConfigurationSelector 给容器导入组件
 *  导入两个组件
 *      AutoProxyRegistrar、ProxyTransactionManagementConfiguration
 * 2）、AutoProxyRegistrar：
 *      给容器注册一个InfrastructureAdvisorAutoProxyCreator组件（也是一个后置处理器）
 *          利用后置处理器机制在创建对象时，包装对象，返回一个代理对象（增强器）, 代理对象执行方法（利用拦截器链进行调用）
 * 3）、ProxyTransactionManagementConfiguration 做了什么？
 *      1.给容器中注册事务增强器：
 *          事务增强器要用事务注解的信息，AnnotationTransactionAttributeSource解析事务注解
 *          事务拦截器：
 *              @see TransactionInterceptor ##： 保存事务属性信息，事务管理器
 *                  底层还是 MethodInterceptor；
 *              在目标方法执行时：
 *                  执行拦截器链；
 *                  事务拦截器：
 *                      1）、先获取事务相关的属性
 *                      2）、获取事务管理器 TransactionManager tm = determineTransactionManager(txAttr);
 *                          如果事先没有添加指定任何 transactionManager 最终会从容器中 按照类型获取一个PlatfromTranssactionManager对象
 *                      3)、执行目标方法
 *                          如果异常，获取到事务管理器，利用事务管理器回滚操作
 *                          如果正常，利用事务管理器提交事务
 *
 */
@EnableTransactionManagement
@Configuration
@ComponentScan("com.lea.tx")
public class TxConfig {

    @Bean
    public DataSource dataSource() throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setUser("root");
        dataSource.setPassword("root");
        dataSource.setDriverClass("com.mysql.jdbc.Driver");
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/test?serverTimezone=UTC");
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() throws PropertyVetoException {
        // spring对@Configuration类会特殊处理，给容器中加组件方法，多次调用都只是从容器中找组件
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource());
        return jdbcTemplate;
    }

    // 注册事务管理器
    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
