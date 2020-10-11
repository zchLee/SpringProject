package com.lea.di01;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author lzc
 * @create 2020.09.29 17:33
 *
 *  @Component:创建对象的，等同与<bean> 的功能
 *         属性： value就是对象的名称，就是bean的id值，
 *                value的值是唯一的，创建的对象在整个spring容器中只有一个
 *                不写默认类名首字母小写
 *         位置：在类的上面
 *
 *     @Component(value="myStudent") 等同于
 *     <bean id="myStudent" class="com.lea.di01.Student"/>
 *
 *  spring中和@Component功能一致，创建对象的注解还有：
 *  1.@Repository（持久层类的上面）: 放在Dao层的实现类上，表示创建dao对象，dao对象能访问数据库的。
 *  2.@Service（用在业务层类的上面）: 放在Service层的实现类上，表示创建Service对象，
 *      Service对象做业务处理，可以有事务功能。
 *  3.@Controller(放在控制器上面)：放在控制器（处理器）上面，创建控制器对象，控制器对象，
 *      能够接受用户提交的参数，显示处理结果
 *  以上三个注解使用语法和@Component一样 都能创建对象，但是这三个注解还有额外的功能。
 *
 *  @Repository，@Service，@Controller是给项目的对象分层
 *
 *  @Component在实体类不是上述分层时，用@Component
 *
 *  @Scope：改变bean声明周期 默认singleton，同xml配置中的解释说明  prototype多例
 *
 *  下面两个注解只能修饰方法
 *  @PostConstruct: 指定bean初始方法
 *  @@PreDestroy： 做定bean销毁方法
 *
 */
//@Component("myStudent")
@Component
@Scope("singleton")
public class Student {

    private String name;

    private Integer age;

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    @PostConstruct
    public void init() {
        System.out.println("bean初始方法---");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("bean销毁方法---");
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
