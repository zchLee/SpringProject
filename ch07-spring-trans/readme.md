1.什么是事务？
	mysql的时候，提出了事务。事务指一组sql语句的集合，集合中有多条sql语句
2.在什么时候想到使用事务？
	当多个操作，涉及到多个表的数据，需要保证这些数据的一致性的时候

	2.1在java代码中写程序，控制事务，此时事务应该放在哪里？
		放在service业务方法上，因为业务方法会调用多个dao方法，执行多个sql语句

3.通常使用JDBC访问数据库，还是mybatis访问数据库怎么处理事务
	前提是要关闭自动提交 autocommit = false;
	3.1 jdbc访问数据库，处理事务 Connection conn； conn.commit(); conn.rollback();
	3.2 mybatis访问数据库，处理事务，SqlSession.commit(); SqlSession.rollback();
	3.3 hibernate访问数据库，处理事务，Session.commit(); Session.rollback();

4. 第3个问题中事务的处理方法有什么不足
	1) 不同数据库访问技术，处理事务的对象，方法不同，需要了解不同数据库访问技术使用事务的原理
	2）掌握多种数据库中，事务的处理逻辑。什么时候提交，什么时候回滚事务
	3）处理事务的多种方法

	总结：多种数据库访问技术，有不同的事务处理机制。对象，方法

5.怎么解决不足
	spring提供了一种能处理事务的统一模型，能使用统一的步骤，方式完成多种不同数据库访问技术的事务处理。
	可以完成mybatis、hibernate、jdbc的事务处理

6.处理事务，需要怎么做，做什么 
	spring处理事务的模型，使用的步骤都是固定的。把事务使用的信息提供给spring就可以了
	1) 事务内部提交，回滚事务，使用事务管理对象，代替开发人员完成commit，rollback，
	事务管理器是一个接口和他的具体实现类。
	接口：PlatfomTransactionManager, 定义了事务的重要方法 commit rollback
	实现类： spring把每种数据库访问技术对应的事务处理类都创建好了
		mybatis-->DataSourceTranctionManager
		hibernate-->HibernateTransactionManager

	怎么使用：
		需要告诉spring，技术栈用的哪种数据库访问技术，
		怎么告诉spring呢
			声明数据库访问技术对应的事务管理器实现类，在spring配置文件中配置bean
			例如：mybatis --> <bean id="xxx" class="...DataSourceTranctionManager"></bean>


	2) 业务方法需要什么样的事务，说明需要事务的类型。
	   说明方法需要的事务
	   2.1） 五个事务隔离级别,都是以ISOLATION_开头，即ISOLATION_xx
			   	DEFAULLT:采用DB默认的事务隔离级别。MySql的默认为REPEATABLE_READ;Oracle默认为READ_COMMITTED
			   	READ_UNCOMMITTED:读未提交。未解决任何并发问题
			   	READ_COMMITTED:读已提交、解决脏读，存在不可重复读或幻读
			   	REPEATABLE_READ:可重复读。解决脏读、不可重复读、存在幻读
			   	SERIALIZABLE：串行化。不存在并发问题
	   2.2） 事务的超时时间：表示一个方法最长的执行时间，如果方法执行时间超过了这个时间，事务就回滚
				单位是秒，整数值，默认是-1。
	   2.3） 事务的传播行为：控制业务方法是不是有事务，是什么样的事务的。
	   			7个传播行为，表示业务方法在调用时，事务在方法之间是如何使用的
	   				**PROPAGATION_REQUIRED**： 支持当前事务； 如果不存在，请创建一个新的。
	   					指定方法必须在事务内执行，若当前存在事务，就加入到当前事务中，若没有当前事务，则创建一个新事务。
	   					这种传播机制是最常见的选择，也是spring的默认事务传播行为。
					**PROPAGATION_REQUIRES_NEW**： 创建一个新事务，如果存在则暂停当前事务。
						总是新建一个事务，若当前存在事务，就把当前事务挂起，直到指定方法的新事务执行完毕后再执行
					**PROPAGATION_SUPPORTS**： 支持当前事务； 如果不存在，则以非事务方式执行。
						指定方法支持当前事务，但若当前没有事务，也可以以非事务方式执行

	   				PROPAGATION_MANDATORY： 支持当前事务； 如果当前事务不存在，则引发异常。
					PROPAGATION_NESTED： 如果当前事务存在，则在嵌套事务内执行，否则，行为类似于PROPAGATION_REQUIRED。
					PROPAGATION_NEVER： 不支持当前事务； 如果当前事务存在，则引发异常。
					PROPAGATION_NOT_SUPPORTED：不支持当前事务； 而是始终以非事务方式执行。

   3） spring提交事务，回滚事务时机（默认）
   		3.1）业务方法执行完毕后，没有异常抛出时，spring在方法执行后提交事务 commit();
   		3.2) 业务方法抛出运行时异常时，spring执行回滚，调用事务管理器的 rollback();
   			运行时异常：RuntimeException与它的子类都是运行时异常。例如NullPointException
   		3.3）业务方法抛出非运行时异常，主要是受查异常时和ERROR时，spring提交事务
			受查异常：在代码中必须处理的异常。比如IOException, SQLException

总结spring的事务
	1.管理事务：事务管理及其实现类
	2.spring的事务是一个统一模型
		1）指定要使用的事务管理器 配置bean
		2）指定哪些类，哪些方法需要加入事务功能
		3）指定方法需要的隔离级别，传播行为，超时

		需要告诉spring，项目中类信息，方法信息，方法的事务传播行为

##### 实现步骤参考06modul

##### spring框架中提供的事务处理方法
1.适合中小项目使用的，注解方案
    spring框架使用aop给业务方法增加事务的功能，<u>使用@Transactional注解增加事务</u>
    @Transactional属于spring框架，方法放在public方法上面，表示当前方法具有事务
    可以给注解的属性赋值，表示具体的隔离级别，传播行为，异常信息等
    属性：
        propagation:用于设置事务传播属性。该属性类型为Propagation枚举，默认类型为Propagation.REQUIRED
        isolation:用于设置事务隔离级别。该属性类型为Isolation枚举，默认类型为Isolation.DEFAULT
        readOnly:用于设置方法对数据库的操作是否是只读。该属性为boolean，默认值为false
        timeout：用于设置本操作与数据库连接的超时时限，单位为秒，类型为int，默认值为-1，没有时限
        rollbackFor:指定异常回滚的异常类，类型为Class[], 默认值为空数组。当然若只有一个异常类时，可以不使用数组
        rollbackForClassName：指定需要回滚的异常类名。类型为String[].默认也为空数组，如果只有一个异常类，也可以不用数组
        noRollbackFor：指定不需要回滚的异常类，类型为class[] 默认值为空数组。若只有一个异常类，不需要用数组
        noRollbackForClassName：指定不需要回滚的异常类名。类型为String[].默认也为空数组，如果只有一个异常类，也可以不用数组
        
    注意：
        @Transactional若使用在方法上，只能用在public方法上，对于非public方法,该方法不会报错也不会执行事务
        因为spring忽略所有非public方法上的@Trancational注解   
        
        
使用@Transactional步骤：
1.需要声明事务管理器对象
    <bean id="xx" class="xxx.DataSourceTransactionManager" />
2.开启事务注解驱动，告诉spring框架，所需要的使用注解方式管理事务
 spring使用aop机制，创建@Transactional所在类代理对象，给方法加入事务功能
 spring给业务方法加入事务：
    在业务方法执行之前，开始事务，在方法执行之后提交或回滚事务。使用aop的环绕通知
    @Around(""事务增加功能的目标方法")
    Object myAround() {
        spring开启事务
        try{
            buy(1001, 20);
            spring提交事务；
        }catch(Exception e) {
            spring回滚事务；
        }
    }