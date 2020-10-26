package com.lea.conf;

import com.lea.aop.LogAspects;
import com.lea.aop.MathCalculator;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author lzc
 * @create 2020-10-22 11:45
 *
 * AOP【动态代理】:
 *      指在程序运行期间动态的将某段代码切入到指定方法指定位置运行的方式；
 *      1、导入aop模块 spring-aspects
 *      2. 定义一个类 MathCalculator 需要在方法执行时打印日志（执行前，执行后，抛出异常）
 *      3. 定义一个 日志 切面类 LogAspects
 *          通知方法：
 *              前置通知(@Before)： 目标方法执行前 执行
 *              后置通知(@After)： 目标方法执行结束之后 执行
 *              返回通知(@AfterReturning)： 在目标方法正常放回 执行
 *              异常通知(@AfterThrowing)： 目标方法出现异常之后，执行
 *              环绕通知(@Around)： 动态代理，手动推进目标方法运行（joinPoint.procced()）
 *      4. 给切面类的目标方法标注 通知类型
 *      5. 将切面类和业务逻辑类（目标方法所在类）都加到容器中；
 *      6. 必须给切面类 加@@Aspect 注解，使spring可以区分 切面类和 非切面类
 *      [7]. 必须给配置类加注解 @EnableAspectJAutoProxy 【开启基于注解的aop模式】
 *           在spring中很多的 @EnableXXX 注解 表示开启某一项功能，替代传统的xml配置
 *
 * AOP原理：【看给容器中注册了什么，这个组件什么时候工作，这个组件的功能是什么】
 *  @EnableAspectJAutoProxy
 *  1.@EnableAspectJAutoProxy是什么？
 *      @Import(AspectJAutoProxyRegistrar.class)； 给容器中导入AspectJAutoProxyRegistrar类
 *          利用AspectJAutoProxyRegistrar自定义给容器中注册bean；
 *          internalAutoProxyCreator=org.springframework.aop.aspectj.annotation.AnnotationAwareAspectJAutoProxyCreator
 *      在容器中注册AnnotationAwareAspectJAutoProxyCreator;  ----》 自动代理创建器
 *  2. AnnotationAwareAspectJAutoProxyCreator：
 *      AspectJAwareAdvisorAutoProxyCreator
 *          -》AbstractAdvisorAutoProxyCreator
 *              -》AbstractAutoProxyCreator
 *                  -》implements SmartInstantiationAwareBeanPostProcessor, BeanFactoryAware
 *                      关注后置处理器（在bean初始化完成前后的作用）
 *   AbstractAutoProxyCreator.setBeanFactory
 *   AbstractAutoProxyCreator.postProcessBeforeInstantiation   // 后置处理器的逻辑
 *   AbstractAutoProxyCreator.postProcessAfterInitialization
 *
 *   AbstractAdvisorAutoProxyCreator.setBeanFactory -》 initBeanFactory
 *  AnnotationAwareAspectJAutoProxyCreator.initBeanFactory
 *
 *
 * 流程:
 *  1.传入配置类，创建IOC容器。
 *  2. 注册配置类，，调用refresh() 刷新容器
 *  3. registerBeanPostProcessors(beanFactory); 注册bean的后置处理器，方便拦截bean的创建
 *      3.1 获取ioc容器已经定义了 需要创建的所有BeanPostProcessors
 *      3.2 给容器中加别的BeanPostProcessor
 *      3.3 优先注册实现了PriorityOrdered接口的BeanPostProcessors;
 *      3.4 再给容器中注册实现了Ordered接口的BeanPostProcessors；
 *      3.5 最后注册没实现优先级接口的BeanPostProcessors
 *      3.6 注册BeanPostProcessors, 实际上就是创建BeanPostProcessors对象，保存在容器中
 *          如何创建internalAutoProxyCreator的BeanPostProcessors【AnnotationAwareAspectJAutoProxyCreator】
 *             3.6.1 创建Bean的实例 -----> doCreateBean(beanName, mbdToUse, args);
 *             3.6.2 给Bean的属性赋值 ——》 populateBean(beanName, mbd, instanceWrapper);
 *             3.6.3 初始化Bean --- 》initializeBean(beanName, exposedObject, mbd);
 *                  3.6.3.1 invokeAwareMethods()                            处理Aware接口的赋值 ;
 *                  3.6.3.2 applyBeanPostProcessorsBeforeInitialization()--》 postProcessBeforeInitialization() 初始化执行前执行。 ，应用后置处理器的BeforeInitialization ;
 *                  3.6.3.3 invokeInitMethods()                             执行自定义的初始化
 *                  3.6.3.4 applyBeanPostProcessorsAfterInitialization() --> postProcessBeforeInitialization(); 执行后置处理器的 初始化之后的方法
 *             3.6.4 BeaProcessor(AnnotationAwareAspectJAutoProxyCreator) 创建成功： --aspectJAdvisorsBuilder
 *       3.7 把BeanProcessors注册到BeamFactory beanFactory.addBeanPostProcessor(postProcessor);
 *====================以上是创建和注册AnnotationAwareAspectJAutoProxyCreator的过程===========
 *      AnnotationAwareAspectJAutoProxyCreator =》 InstantiationAwareBeanPostProcessor
 * 4. finishBeanFactoryInitialization(beanFactory)； 完成BeanFactory初始化操作；创建剩下的单实例Bean
 *      4.1 遍历获取容器中是所有的Bean，依次创建对象 getBean(beanName)
 *          getBean() -> doGetBean() ->  getSingleton() ->
 *      4.2 创建bean
 *          【AnnotationAwareAspectJAutoProxyCreator在所有创建之前会有一个拦截，InstantiationAwareBeanPostProcessor,会调用postProcessBeforeInstantiation()】
 *          4.2.1 先从缓存中获取当前Bean是否被创建了，如果能获取到，说明bean是被创建过的，直接使用，否则再创建；
 *              只要创建好的bean都会被缓存起来
 *          4.2.2 createBean()； 创建bean  AnnotationAwareAspectJAutoProxyCreator会在任何Bean创建之前先尝试返回bean的实例
 *              【BeanPostProcessor是在Bean对象创建完成初始化前后调用的】
 *              【InstantiationAwareBeanPostProcessor是在创建Bean实例之前先尝试用后置处理器返回对象的】
 *             4.2.2.1 resolveBeforeInstantiation(); 解析BeforeInstantiation
 *                  希望后置处理器在此 能返回一个代理对象，如果能返回代理对象，就使用，如果不能就继续调用doCreateBean(beanName, mbdToUse, args); 创建Bean
 *                  4.2.2.1.1 后置处理器先尝试返回对象
 *                      bean = applyBeanPostProcessorsBeforeInstantiation(targetType, beanName);
 *                          拿到所有后置处理器， if (bp instanceof InstantiationAwareBeanPostProcessor) 就执行
 *                              ibp.postProcessBeforeInstantiation(beanClass, beanName);
 * 					    if (bean != null)
 * 						    bean = applyBeanPostProcessorsAfterInitialization(bean, beanName);
 *
 *             4.2.2.2 doCreateBean() 真正创建一个bean实例，和3.6流程一样
 *             4.2.2.3
 *
 * AbstractAdvisorAutoProxyCreator【InstantiationAwareBeanPostProcessor】的作用：
 *  1. 每个bean创建之前，调用postProcessBeforeInstantiation()
 *      普通类和LogAspect的创建
 *       1.1 判断bean是否在advisedBeans中（保存了所有需要的增强的bean，指目标类：MathCalculator）
 *       1.2 判断当前bean是否是基础类型Advice、Pointcut、Advisor、opInfrastructureBean、或者是否是isAspect\(@Aspect) 切面类
 *       1.3 是否需要跳过
 *          1.3.1 获取候选的增强器（切面的通知方法） 【List<Advisor> candidateAdvisors】
 *           每个封装的通知方法的增强器是InstantiationModelAwarePointcutAdvisor类型；
 *           判断每一个增强器是否AspectJPointcutAdvisor，是则返回false
 *          1.3.2 永远返回false
 *  2、创建对象
 *  3、创建对象之后 调用postProcessAfterInitialization()
 *      wrapIfNecessary(bean, beanName, cacheKey);// 包装如果需要的
 *      3.1 getAdvicesAndAdvisorsForBean() 获取当前bean的所有增强器（通知方法） Object[]
 *              3.1.1 findEligibleAdvisors()->findCandidateAdvisors() 中获取findAdvisorsThatCanApply()可以用的增强器【哪些方法可以切入当前方法的】
 *              3.1.2 sortAdvisors() 给增强器排序
 *      3.2 保存当前bean在advisedBeans中
 *      3.3 如果当前bean需要增强，创建当前bean的代理对象
 *          3.3.1 buildAdvisors(); 获取所有的增强器（通知方法）
 *          3.3.2 proxyFactory.addAdvisors(advisors) 保存到代理工厂；
 *          3.3.3 创建代理对象
 *              1. 根据不同策略选择不同方式创建代理
 *               JdkDynamicAopProxy(config);        // 有接口，就用JDK动态代理
 * 			     ObjenesisCglibAopProxy(config);    // 没接口，就用CGLIB动态代理
 *      3.4 给容器中返回当前组件用cglib增强的代理对象
 *      3.5 以后从容器中获取到这个组件的代理对象，执行目标方法的时候，就是执行通知方法的流程
 *
 * ### 目标方法执行：
 *      容器中保存了组件的代理对象（增强后的代理对象），这个对象里保存了详细信息（比如增强器，目标对象等）
 *      1）CglibAopProxy.intercept() 拦截目标方法的执行
 *      2）根据ProxyFactory对象获取目标方法的拦截器链
 *          List<Object> chain = this.advised.getInterceptorsAndDynamicInterceptionAdvice(method, targetClass);
 *          1) List<Object> interceptorList保存所有拦截器
 *              一个ExposeInvocationInterceptor 和 4个增强器
 *          2） 遍历所有的增强器，将其转为Interceptor；
 *              registry.getInterceptors(advisor)
 *              如果是MethodInterceptor类型，直接加入集合中
 *              如果不是，使用AdvisorAdapter将增强器转为MethodInterceptor[]
 *
 *      3）如果没有拦截器链，直接执行目标方法
 *          拦截器链（每个通知方法又被包装为方法拦截器，利用MethodInterceptor机制）
 *      4）如果有拦截器链，把需要执行的目标对象，目标参数，目标方法，拦截器链等所有信息传入，创建一个 CglibMethodInvocation 对象，
 *          并调用     Object retVal = mi.proceed()
 *      5）拦截器链的触发过程
 *          5.1 如果没有拦截器，直接执行目标方法，或者拦截器的索引和拦截器数组-1大小一样（执行到最后一个拦截器）执行目标方法
 *          5.2 链式获取每一个拦截器，拦截器执行invoke方法，每个拦截器等待下一个拦截器执行完成后返回再来执行；
 *              拦截器链的机制保证通知方法与目标方法的执行顺序
 *             （递归实现，调用不同AspectJXXXXXXAdvice.invoke()方法）
 *
 * 总结：
 *  1.@EnableAspectJAutoProxy 开启AOP功能
 *  2、@EnableAspectJAutoProxy会给容器中注册一个组件AbstractAdvisorAutoProxyCreator
 *  3、AbstractAdvisorAutoProxyCreator是一个后置处理器
 *  4、容器的创建流程：
 *      4.1 registerBeanPostProcessors(beanFactory); 注册所有的后置处理器；创建AbstractAdvisorAutoProxyCreator对象
 *      4.2 finishBeanFactoryInitialization(beanFactory); 初始化剩下的单实例bean
 *          4.2.1 创建业务逻辑组件和切面组件
 *          4.2.2 AbstractAdvisorAutoProxyCreator 拦截组件的创建过程
 *          4.2.3 组件创建完之后，判断组件是否需要增强
 *              是：切面的通知方法 包装成增强器（Advisor）; 给业务逻辑组件创建一个代理对象（CGLIB）;
 *  5、执行目标方法：
 *      5.1、代理对象执行目标方法：
 *      5.2、CglibAopProxy.intercept()
 *          得到目标方法的拦截器链(增强器包装成拦截器MethodInterceptor)
 *          利用拦截器的链式机制，一次进入每一个拦截器进行执行
 *          执行效果：
 *              前置通知-》目标方法-》后置通知-(正常)-》返回通知
 *              前置通知-》目标方法-》后置通知-(异常)-》异常通知
 *
 *
 *
 */
//@EnableAspectJAutoProxy
//@Configuration
public class MainConfigOfAOP {

    @Bean
    public MathCalculator calculator() {
        return new MathCalculator();
    }

    @Bean
    public LogAspects logAspects() {
        return new LogAspects();
    }

}
