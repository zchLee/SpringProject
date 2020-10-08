### 动态代理
> 可以在程序执行过程中，创建代理对象，通过代理对象执行方法，
> 给目标方法增加额外的功能（功能增强）

##### jdk动态代理实现步骤：必须要有接口
1. 创建目标类，增加方法
2. 创建InvocationHandler接口实现类, 在这个类实现目标方法增加功能
3. 使用jdk中 Proxy代理类，创建对象。实现创建对象的能力


### AOP
1.动态代理
> 实现方式：
>>jdk动态代理，使用jdk中的proxy，Method，InvocationHandler创建代理对象
>>       jdk动态代理要求目标类必须实现接口
>
>>           cdlib动态代理： 被代理类必须能被继承
>> 第三方的工具库，创建代理对象，原理的继承，通过继承目标类，创建子类，
>> 子类就是代理对象。要求目标类必须能被继承，方法也不能是final的

2. 动态代理的作用
  * 1）、在目标类源代码不改变的情况下，增加功能
  * 2）、减少代码重复性
  * 3）、专注业务逻辑代码
  * 4）、解耦合，让你的业务功能和日志，事务非业务功能分析
  
3. AOP：面向切面编程，基于动态代理的，可以使用jdk，cglib两种代理方式
    AOP是动态代理的规范化，把动态代理的实现步骤，方式都定义好，让开发人员用一种统一方式，使用动态代理

4. AOP(Aspect orient Programming):面向切面编程，
    > Aspect: 切面，给类增加功能，就是切面，eq：日志，事务等都是切面
        >> :切面的特点：一般都是非业务代码，独立使用
 
##### AOP实现
    aop是一个规范，是动态代理的一个规范化，一个标准
    aop的技术实现框架：
        1.spring：spring在内部实现了aop规范，能做aop的工作
            spring主要在事务处理时使用了aop
        2.aspectJ：开源的专门做AOP的框架，spring框架中集成了aspect的功能
            aspectJ实现AOP有两种方式：
                1、使用xml配置文件：配置全局事务
                2、使用注解，aspectJ有5个注解 
 ##### aspectJ的使用
 ###### 一.切面执行的时间
    1. 切面的执行时间、此执行时间在规范中叫做Advice（通知，增强）
       在aspectJ框架中使用注解表示。也可以使用xml配置
       1） @Befor
       2） @AfterRetruning 
       3） @Around
       4） @AfterThrowing
       5） @After
    2. 表示切面执行的位置，使用的是切入点表达式。
    excution(modifiers-pattern? ret-type-pattern declaring-type-pattern?name-pattern(param-oattern) throws-pattern?)
        带? 表示可选，以空格分隔
    modifiers-pattern ： 权限修饰符（可省略）
    ret-type-pattern： 返回类型
    declaring-type-pattern？： 包名类名（可省略）；name-pattern(param-oattern)：方法名(参数类型和个数)
    throws-pattern?： 抛出异常类型（可省略）
    以上都以pattern结尾，表示可以用通配符做匹配
    
    
  