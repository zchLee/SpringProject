package com.lea.conf;

import com.lea.bean.Car;
import com.lea.bean.Cat;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author lzc
 * @create 2020-10-21 14:19
 * Bean管理的生命周期：
 *      bean创建-初始化-销毁
 * 容器管理bean的生命周期：
 *      1.可以自定义初始化和销毁方法：容器在bean进行当前生命周期的时候调用我们自定义的初始化和销毁方法
 *      2.Bean实现 InitializingBean, DisposableBean
 *      3.可以使用JSR250 规范 @PostConstruct 初始化方法，@PreDestroy 销毁方法
 *   重要4.BeanPostProcess：bean后置处理器,在bean初始化进行一些处理工作，会作用在每一个bean的初始化方法
 *          postProcessBeforeInitialization: 在初始化之前执行
 *          postProcessAfterInitialization： 在初始化方法之后执行
 *
 *    BeanPostProcess在spring中实现类颇多，多用于Bean赋值，注入其他组件，@Autowired 生命周期注入，@Async，xxxBeanPostProcess
 *
 * 构造（对象创建）
 *      单实例：在容器启动时创建对象
 *      多实例：在从容器中获取对象的时候，创建对象
 * 初始化：
 *      对象创建成功，并赋初值后，再调用初始方法
 *  销毁：
 *      在单实例时，容器关闭时，对象执行销毁方法
 *      多实例时，容器不再管理
 *
 */
@Configuration
@ComponentScan("com.lea")
public class MainConfigOfLifeCycle {

    @Bean(initMethod = "init", destroyMethod = "destroy")  // 指定Bean 初始化方法 和销毁方法
    public Car car() {
        return new Car();
    }

}
