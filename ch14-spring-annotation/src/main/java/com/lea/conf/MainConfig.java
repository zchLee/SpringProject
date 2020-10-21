package com.lea.conf;

import com.lea.bean.ColorFactoryBean;
import com.lea.bean.OtherColor;
import com.lea.bean.OtherRed;
import com.lea.bean.User;
import com.lea.condition.WangWuCondition;
import com.lea.condition.ZHangSanCondition;
import com.lea.importselector.MyImportBeanDefinitionRegistrar;
import com.lea.importselector.MyImportSelector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Lazy;

/**
 * @author lzc
 * @create 2020-10-20 17:36
 */
// 配置类==配置文件

/*@ComponentScan("com.lea") // 包扫描
//excludeFilters: 扫描时，排除那些组件
//includeFilters：扫描时，只需要包含那些组件 只包含时需要配置默认全部扫描要关闭(useDefaultFilters= false)
    FilterType.ANNOTATION 按照注解类型过滤
    FilterType.ASSIGNABLE_TYPE 按照指定类型过滤
	REGEX       正则表达式来匹配
    CUSTOM      自定义匹配（需要实现org.springframework.core.type.filter.TypeFilter   ）
//@ComponentScan(value = "com.lea", excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Controller.class)})

*/
//@ComponentScan(useDefaultFilters = true, value = "com.lea"
//        includeFilters = {
//            @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Service.class),
//            @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = UserDao.class),
//            @ComponentScan.Filter(type = FilterType.CUSTOM, classes = MyTypeFilter.class)
//        }
//        )
//@ComponentScan
//@Configuration  // 告诉spring这是一个配置类
//@Import({OtherColor.class, OtherRed.class, MyImportSelector.class, MyImportBeanDefinitionRegistrar.class})  // 导入组件，id默认是全类名
public class MainConfig {

    @Bean // 给容器注册bean，类型返回值的类型，id默认是用方法名作为id
//    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)  // 调整作用域
    @Lazy // 懒加载，容器启动时，不创建对象，在第一次获取对象实例时创建，并初始化，默认是单实例的
    public User user() {
        return new User("lisi","12345");
    }

    /*
        @Configuration：按照一定条件进行判断，满足条件就在容器中注册bean
        在类上配置，就表示此类统一配置
     */
    @Conditional({ZHangSanCondition.class})
    @Bean("zhangsan")
    public User user01() {
        return new User("zhangsan","12345");
    }

    @Conditional(WangWuCondition.class)
    @Bean("wangwu")
    public User user02() {
        return new User("wangwu","12345");
    }

    /**
     * 给容器中注册组件
     *   1.包扫描+组件标注注解（@Controller、@Service、@Repository、@Component） 这存在局限性，只能扫描自定义的
     *   2.@Bean（导入第三方包里的组件）
     *   3.@Import（快速给容器中导入组件）
     *      3.1 @Import（导入容器中的组件）：容器中就会自动注册这个组件；
     *      3.2 ImportSelector： 返回需要导入的全类名数组；
     *      3.3 ImportBeanDefinitionRegistrar :  手动注册bean到容器中
     *   4.使用spring提供的FactoryBean（工厂Bean）
     */
    @Bean
    public ColorFactoryBean colorFactoryBean() {
        return new ColorFactoryBean();
    }
}
