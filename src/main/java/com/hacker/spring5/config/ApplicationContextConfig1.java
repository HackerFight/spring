package com.hacker.spring5.config;

import com.hacker.spring5.bean.Air;
import com.hacker.spring5.bean.Cat;
import com.hacker.spring5.bean.Person;
import com.hacker.spring5.bean.Student;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import sun.util.locale.provider.FallbackLocaleProviderAdapter;

/**
 * @author Hacker
 * @date：2018/12/21
 * @project spring
 * @describe
 *   测试：
 *    @Configuration
 *    @Bean
 *    @Lazy
 *    @Scope
 *    bean 的依赖
 *    初始化和销毁
 */

/**
 *  添加 @Configuration 表示这个类是一个配置类，等同于以前的  applicationContext.xml
 */
@Configuration
public class ApplicationContextConfig1 {

    /**
     *  @Bean() 想容器中注册一个bean，此时bean 的名字默认取 方法名
     *  @bean("personBean") 想容器中注册一个bean，此时bean 的名字是 value 属性指定的
     *
     *  @Scope()  bean 的模式，单例或者多例
     *
     *  @lazy() 是否是懒加载（当获取的时候再去创建对象，而不是启动就去创建）  默认是true
     */
    @Lazy(value = false)
    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
    @Bean("personBean")
    public Person createPerson() {
        return new Person("hacker", 24);
    }

    @Bean
    public Cat buildCat() {
        return new Cat();
    }

    /**
     * bean 的依赖：
     *  <pre>
     *   以前在xml 中是： 通过 ref 来引用其他的bean，在全注解中改如何使用呢？
     *       <bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
     *              <property name="dataSource" ref="asset"/>
     *       </bean>
     *    </pre>
     *
     *   其实是一样的，直接作为参数传递进来就可以了，spring 他会将参数中的bean 以来进来
     *   注意：如果容器中不存在这个bean，那么是无法作为参数的，比如我上面没有创建 cat 对象，那么
     *         这里讲 Cat 作为参数传递进来就会报错，哪怕是 String 类型的参数也不行
     *
     *   所以：这个方法的参数不能乱写，必须要保证在容器中存在才可以
     */
    @Bean(value = "student")
    public Student buildStudent(Cat cat){
        return new Student(cat);
    }

    /**
     * 测试初始化和销毁方法
     * 初始化和销毁有三种方式：
     *    1）通过 @Bean 注解的  initMethod  和  destroyMethod 属性（xml 中也一样有）
     *    2）实现  InitializingBean  和 DisposableBean 接口
     *    3）注解方式：标注 @PostConstruct 和  @PreDestroy 注解
     */
    @Bean(value = "air", initMethod = "initColor", destroyMethod = "destroyBean")
    public Air buildAir() {
        return new Air();
    }
}
