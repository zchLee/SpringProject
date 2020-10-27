package com.lea.ext;

import com.lea.bean.Person;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

/**
 * @author lzc
 * @create 2020-10-26 17:30
 * 扩展原理
 *  1.BeanPostProcessor; bean后置处理器，bean创建对象 初始化前后进行拦截工作的
     *  BeanFactoryPostProcessor； beanFactory后置处理器；
     *      在beanFactory标准初始化之后调用；所有的bean定义已经保存加载到beanFactory，但是bean的实例还未创建
     * 1）、ioc容器创建对象
     * 2）、invokeBeanFactoryPostProcessors(beanFactory); 执行BeanFactoryPostProcessors；
     *      如何找到所有的BeanFactoryPostProcessors并执行他们的方法；
     *          1）、直接在BeanFactory中找到所有类型是BeanFactoryPostProcessors的组件，并执行他们的方法
     *          2）、在初始化创建其他组件前执行
 *
 *  2.BeanDefinitionRegistryPostProcessor extends BeanFactoryPostProcessor
 *      postProcessBeanDefinitionRegistry()
 *      在所有bean定义信息将要被加载，bean实例还未创建时 执行
 *
 *      优先于BeanFactoryPostProcessor执行，
 *      可以利用BeanDefinitionRegistryPostProcessor在容器中添加一些额外的组件
 *
 *    原理：
 *      1）：ioc创建对象
 *      2）：refresh() -> invokeBeanFactoryPostProcessors(beanFactory);
 *      3）：从容器中获取所有BeanDefinitionRegistryPostProcessor组件。
 *          3.1 触发所有的postProcessBeanDefinitionRegistry() 方法
 *          3.2 再来触发 postProcessBeanFactory() 方法 ;
 *      4）、 从容器中找BeanFactoryPostProcessor类型组件，然后依次执行postProcessBeanFactory()
 * 3.ApplicationListener：监听容器中发布的事件。事件驱动模型开发
 *      public interface ApplicationListener<E extends ApplicationEvent> extends EventListener
 *          监听 ApplicationEvent 及其下面的子事件：
 *     开发监听器步骤： （@EventListener 可以给任意方法，实现事件监听，不用重写接口） --看实现类-》 EventListenerMethodProcessor
 *      1.写一个监听器来监听每个事件（ApplicationEvent及其子类）
 *      2.把监听器加入容器中
 *      3.只要容器中有相关事件的发布，我们就能监听到这个事件
 *          eg：ContextRefreshedEvent：容器刷新完成（所有bean都完全创建），spring会发布这个事件
 *              ContextClosedEvent：spring关闭时，会发布此事件
 *      4. 怎么发布事件
 *           通过容器 ac.publishEvent(new ApplicationEvent(new String(" 这是一个事件")) {});
 * 原理：
 *  ContextRefreshedEvent、com.lea.TestExt$1[source= 这是一个事件]、ContextClosedEvent：
 *  ContextRefreshedEvent事件:
 *      1.容器创建对象：refresh()
 *      2.finishRefresh() 容器刷新完成后，会发布 ContextRefreshedEvent 事件
 *   事件发布流程：
 *      1.ContextRefreshedEvent事件
 *          1.1 容器创建对象：refresh();
 *          1.2 finishRefresh(); 容器刷新完成
 *          1.3 publishEvent(new ContextRefreshedEvent(this));
 *                  1.3.1 获取事件的多播器(派发器); getApplicationEventMulticaster()
 *                  1.3.2 multicastEvent 派发事件
 *                  1.3.3 获取到所有的ApplicationListener；  getApplicationListeners(event, type)
 *                      如果 [ Executor executor = getTaskExecutor() ] executor 不为空，可以支持使用Executor进行异步派发
 *                      executor为空，直接执行listener方法； invokeListener(listener, event);
 *
 *                      拿到listener回调onApplicationEvent方法
 * 【事件的多播器(派发器)】
 *      1.容器创建对象 refresh();
 *      2、initApplicationEventMulticaster(); 初始化ApplicationEventMulticaster
 *          先从 容器中 找有没有 id = "applicationEventMulticaster" 的组件
 *          如果有，直接赋值给派发起
 *          如果没有 this.applicationEventMulticaster = new SimpleApplicationEventMulticaster(beanFactory);
 * 【容器中有哪些监听器 getApplicationListeners()】
 *     1.容器创建对象 refresh();
 *     2. registerListeners(); 注册监听器，把他们注册到applicationEventMulticaster 中
 *      String[] listenerBeanNames = getBeanNamesForType(ApplicationListener.class, true, false);
 *      for (String listenerBeanName : listenerBeanNames) {
 * 			getApplicationEventMulticaster().addApplicationListenerBean(listenerBeanName);  // 注册
 *       }
 *
 *  （@EventListener）SmartInitializingSingleton 原理：  --- 》afterSingletonsInstantiated();
 *      1.容器创建对象 refresh();
 *      2. finishBeanFactoryInitialization(beanFactory);   初始化所有的单实例bean
 *          1. 先创建所有单实例bean getBean()
 *          2. 获取所有创建好的单实例bean，判断是否是  SmartInitializingSingleton
 *              是就调用afterSingletonsInstantiated();
 *
 *
 */
@Configuration
@ComponentScan("com.lea.ext")
public class ExtConfig {

    @Bean
    public Person person() {
        return new Person();
    }

    // 监听事件
    @EventListener(classes = ApplicationEvent.class)
    public void listener(ApplicationEvent event) {
        System.out.println("listener 方法监听到的信息：" + event);
    }
}
