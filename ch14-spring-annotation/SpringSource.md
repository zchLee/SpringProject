Spring容器refresh()【容器的创建以及刷新】
1.prepareRefresh();  刷新前的预处理工作
    1.initPropertySources(); 初始化一些属性设置，子类自定义个性化的属性设置
    2.getEnvironment().validateRequiredProperties(); 检验属性的合法
    3.this.applicationListeners.addAll(this.earlyApplicationListeners); 保存容器中的一些早期的事件
2.obtainFreshBeanFactory(); 获取BeanFactory；
    1、refreshBeanFactory() 刷新beanFactory
        创建一个this.beanFactory = new DefaultListableBeanFactory();
        设置id；
    2、getBeanFactory(); 返回GenericApplicationContext创建的beanFactory对象
    3、将创建的BeanFactory【DefaultListableBeanFactory】返回；
3.prepareBeanFactory(beanFactory); beanFactory的预准备工作（对beanFactory进行一些设置）；
    1、设置BeanFactory的类加载器，支持表达式解析器等...
    2、添加部分BeanPostProcessor【ApplicationContextAwareProcessor】
    3、设置忽略的自动装配的接口EnvironmentAware.class、EmbeddedValueResolverAware.class、xxx
    4、注册可以解析的自动装配；我们能直接在任何组件中 
        自动装配自动注入：BeanFactory、ResourceLoader、ApplicationEventPublisher、ApplicationContext
    5、添加BeanPostProcessor后置处理器【ApplicationListenerDetector】
    6、添加编译时的AspectJ支持；
    7、给BeanFactory中注册一些能用的组件；
        environment【ConfigurableEnvironment】、 标准环境信息
        systemEnvironment【Map<String, Object>】、 配置文件变量
        systemEnvironment【Map<String, Object>】 系统的环境变量
4.postProcessBeanFactory(beanFactory); BeanFactory准备工作完成后的后置处理工作
    1、子类通过重写这个方法，在beanFactory创建并预准备完成以后做进一步设置
===========================beanFactory的创建及预准备工作===============================================
5.invokeBeanFactoryPostProcessors(beanFactory);  执行 BeanFactoryPostProcessor 的postProcessBeanFactory方法
    BeanFactoryPostProcessors：BeanFactory的后置处理器。在beanFactory标准初始化之后执行的执行
    两个接口:BeanDefinitionRegistryPostProcessor、BeanFactoryPostProcessor
    1、执行BeanFactoryPostProcessor的方法
        1）、获取所有的 BeanDefinitionRegistryPostProcessor
        2)、先执行实现了 PriorityOrdered 优先级接口的 BeanDefinitionRegistryPostProcessor 、   
            invokeBeanDefinitionRegistryPostProcessors(currentRegistryProcessors, registry);
                postProcessor.postProcessBeanDefinitionRegistry(registry);
        3）、再执行实现了 Ordered 顺序接口的BeanDefinitionRegistryPostProcessor
                postProcessor.postProcessBeanDefinitionRegistry(registry);
        4）、最后执行没有实现任何优先级或者顺序接口的 BeanDefinitionRegistryPostProcessor ;
             postProcessor.postProcessBeanDefinitionRegistry(registry);
        5)、 再获取 BeanFactoryPostProcessor 并执行其方法
        7)、先执行实现了 PriorityOrdered 优先级接口的 BeanFactoryPostProcessor 、   
            invokeBeanDefinitionRegistryPostProcessors(currentRegistryProcessors, registry);
                postProcessor.postProcessBeanFactory(beanFactory);
        8）、再执行实现了 Ordered 顺序接口的 BeanFactoryPostProcessor
                postProcessor.postProcessBeanFactory(beanFactory);
        9）、最后执行没有实现任何优先级或者顺序接口的 BeanFactoryPostProcessor ;
             postProcessor.postProcessBeanFactory(beanFactory);
6.invokeBeanFactoryPostProcessors(beanFactory); 注册bean的后置处理器 BeanFactoryPostProcessor【拦截bean创建的过程】 
    不同接口类型的 BeanPostProcess：在Bean创建前后的执行时机是不一样的
    BeanFactoryPostProcessor\ 
    DestructionAwareBeanPostProcessor\
    InstantiationAwareBeanPostProcessor\
    SmartInstantiationAwareBeanPostProcessor\
    MergedBeanDefinitionPostProcessor\  放在internalPostProcessors
    1）、获取所有的 BeanPostProcessor，后置处理器都默认可以通过 PriorityOrdered, Ordered 执行优先级
    2）、先注册实现 PriorityOrdered 优先级接口的 BeanPostProcess
        beanFactory.addBeanPostProcessor(postProcessor); 把每个 BeanPostProcess 添加到beanFactory中
    3）、再注册实现 Ordered 并添加到beanFactory中
    4）、再注册没有实现任何优先级接口的 BeanPostProcessor ， 添加到beanFactory中
    5）、最终注册 MergedBeanDefinitionPostProcessor 
    6）、注册一个ApplicationListenerDetector；来在BeanFactory创建完成后检查是否有ApplicationListener，
        有： this.applicationContext.addApplicationListener((ApplicationListener<?>) bean);
7. initMessageSource(); 初始化MessageSource组件（做国际化功能；消息绑定；消息解析）；
    1)、getBeanFactory(); 获取beanFactory
    2）、beanFactory.containsLocalBean(MESSAGE_SOURCE_BEAN_NAME) 是否存在 此组件
        存在，直接获取并赋值给 this.messageSource
        不存在，DelegatingMessageSource dms = new DelegatingMessageSource(); 
        this.messageSource = dms;
        beanFactory.registerSingleton(MESSAGE_SOURCE_BEAN_NAME, this.messageSource);
        // 注册bean 并赋值给 this.messageSource
        作用：    MessageSource：取出国际化配置文件中的某个key信息，能按照区域信息获取 getMessage();
8. initApplicationEventMulticaster();       初始化事件派发器
    1）、获取bean工厂
    2）、beanFactory.containsLocalBean("applicationEventMulticaster") 是否存在
        存在： this.applicationEventMulticaster = beanFactory.getBean(APPLICATION_EVENT_MULTICASTER_BEAN_NAME, ApplicationEventMulticaster.class);
        不存在：this.applicationEventMulticaster = new SimpleApplicationEventMulticaster(beanFactory);
                beanFactory.registerSingleton(APPLICATION_EVENT_MULTICASTER_BEAN_NAME, this.applicationEventMulticaster);
              // new一个 SimpleApplicationEventMulticaster 并注册到beanFactory中 供下次使用
9. onRefresh();  留给子容器 刷新容器
    1）、子类重写这个方法，子类可以在容器刷新时，自定义逻辑 onRefresh()
10. registerListeners();  检查所有监听器（listener），并注册
    1）、 String[] listenerBeanNames = getBeanNamesForType(ApplicationListener.class);  从容器中拿到所有监听器
    2)、 getApplicationEventMulticaster().addApplicationListenerBean(listenerBeanName); 分别将监听器派发到事件派发器中
    3）、获取早期事件，如果事件不为空，派发之前步骤产生的事件
11. finishBeanFactoryInitialization(beanFactory);       初始化所有剩下的单实例bean
    beanFactory.preInstantiateSingletons();             初始化后剩下的单实例bean
        1）、获取容器中所有的bean，依次进行初始化和创建对象
        2）、获取Bean的定义信息；RootBeanDefinition
        3）、if (!bd.isAbstract() && bd.isSingleton() && !bd.isLazyInit()) 非抽象类、是单例、不是懒加载的，满足条件就创建
            isFactoryBean(beanName) 判断是否是实现 FactoryBean 接口；
                是：做一些处理 再创建bean
                不是：直接调用 getBean(beanName); 创建对象  和通过容器获取bean是一个方法
                    -》doGetBean(name, null, null, false);
                    -》getSingleton(beanName)  从singletonObjects（所有被创建的单实例bean都被保存在这个map集合中）获取bean实例，
                    -》缓存中取不到，开始Bean创建流程：
                    -》markBeanAsCreated(beanName); 标记当前bean已经开始创建，为了多线程情况下，bean多次创建
                    -》getMergedLocalBeanDefinition(beanName);  获取bean的定义信息
                    -》mbd.getDependsOn();           获取当前bean的依赖的其他bean，如果有，调用 getBean() 方法创建依赖的bean对象
                    -》启动单实例的bean创建流程
                        -》createBean(beanName, mbd, args);
                        -》resolveBeforeInstantiation(beanName, mbdToUse);  让 BeanPostProcessors 先拦截返回代理对象
                            InstantiationAwareBeanPostProcessor：提前执行；
                            触发 postProcessBeforeInstantiation（）如果有返回值 触发-》postProcessAfterInstantiation（）
                            有代理对象 就直接放回，没有就继续执行
                        -》doCreateBean(beanName, mbdToUse, args);
                            -》createBeanInstance(beanName, mbd, args);    创建Bean实例
                            -》applyMergedBeanDefinitionPostProcessors(mbd, beanType, beanName);
                                调用MergedBeanDefinitionPostProcessor 的 postProcessMergedBeanDefinition(); 后置处理器 
                            -》populateBean(beanName, mbd, instanceWrapper);   【bean属性赋值】
                                赋值之前
                                -》执行 InstantiationAwareBeanPostProcessor 后置处理器的 .postProcessAfterInstantiation() 方法
                                -》执行 InstantiationAwareBeanPostProcessor 后置处理器 postProcessProperties() 方法
                                ------------------上面是赋值的准备工作--------------------
                                -》applyPropertyValues(beanName, mbd, bw, pvs); 为bean进行赋值操作
                            -》initializeBean(beanName, exposedObject, mbd);       【bean初始化】
                                -》invokeAwareMethods(beanName, bean);           【执行Aware接口方法】 
                                    BeanNameAware.setBeanName(beanName);、
                                    BeanClassLoaderAware.setBeanClassLoader(bcl)、
                                    BeanFactoryAware.setBeanFactory(AbstractAutowireCapableBeanFactory.this) 接口的方法
                                -》applyBeanPostProcessorsBeforeInitialization(wrappedBean, beanName);       【执行所有后置处理器初始化之前的方法】
                                    BeanPostProcessor.postProcessBeforeInitialization(result, beanName);
                                -》invokeInitMethods(beanName, wrappedBean, mbd);            【调用初始化方法】
                                    有两种调用方式
                                    -》1.实现了InitializingBean，重写afterPropertiesSet()方法
                                    -》2.自定义初始化方法  mbd.getInitMethodName(); mbd（bean信息）
                                -》applyBeanPostProcessorsAfterInitialization(wrappedBean, beanName);   【执行所有后置处理器初始化之后的方法】
                                    BeanPostProcessor.postProcessAfterInitialization
                            -》registerDisposableBeanIfNecessary(beanName, bean, mbd);       【注册Bean的销毁方法】
                        -》addSingleton(beanName, singletonObject);          【将创建的bean添加到缓存中 singletonObjects Map集合】
                    ioc容器就是这些Map；多种多样的Map保存单实例信息，环境信息等...
            -》调用所有Bean 实现了 SmartInitializingSingleton.afterSingletonsInstantiated(); 方法
12.finishRefresh();         【完成BeanFactory的初始化创建工作：IOC创建完成】
    -》initLifecycleProcessor();     初始化生命周期有关的后置处理器 LifecycleProcessor
        默认从容器中找 LifecycleProcessor 的实现类组件，可以在BeanFactory的生命周期关闭时 做处理
        如果没有 new DefaultLifecycleProcessor(); 并注册到beanFactory容器中
            void onRefresh();
            void onClose();
    -》getLifecycleProcessor().onRefresh();      拿到生命周期处理器，回调onRefresh() 方法（刷新完成）
    -》publishEvent(new ContextRefreshedEvent(this));  发布一个容器刷新完成的事件
    -》LiveBeansView.registerApplicationContext(this);  // Participate in LiveBeansView MBean, if active.
------------------------------总结--------------------------------------
1.spring容器 在启动的时候，会保存所有注册进来的Bean定义信息，
    1、使用xml注册 <bean>
    2、使用注解注册 @Component、@Bean..
2.spring容器在 合适的时机 创建这些Bean
    1、用到这些Bean的时候，利用getBean() 创建Bean, 创建好久放在Bean容器中
    2、统一创建剩下所有的Bean的时候 finishBeanFactoryInitialization(beanFactory);
3.后置处理器【非常重要】
    1、每个Bean创建完成后，会用后置处理器增强bean的功能 
        AutowiredAnnotationBeanPostProcessor(自动注入)、
        AnnotationAwareAspectJAutoProxyCreator(自动代理)
        AsyncAnnotationBeanPostProcessor(支持异步处理的)
4.事件驱动模型【重要】：
    ApplicationListener: 事件监听；
    ApplicationEventMulticaster 事件的派发