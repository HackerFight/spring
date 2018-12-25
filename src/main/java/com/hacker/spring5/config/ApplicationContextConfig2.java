package com.hacker.spring5.config;

import com.hacker.spring5.bean.Person;
import com.hacker.spring5.condition.LinuxConditional;
import com.hacker.spring5.condition.WindowConditional;
import com.hacker.spring5.factory.HackerFactoryBean;
import com.hacker.spring5.register.BeanImportBeanDefinitionRegistrar;
import com.hacker.spring5.register.BeanImportSelector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Controller;

/**
 * 向容器注册bean 的方式
 *    1. @Bean
 *    2. @ComponentScan
 *    3. @Import
 *          -- ImportSelector
 *          -- ImportBeanDefinitionRegistrar
 *    4. 通过实现 FactoryBean 接口也可以向容器注册bean
 *
 *    @Conditional -按条件注册
 */

@ComponentScan(basePackages = {"com.hacker.spring5"},
         excludeFilters = {
                          @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {Controller.class}), //排除指定的注解
                          @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {ApplicationContextConfig1.class}), //排除指定的类
                           },
         useDefaultFilters = false //不要使用默认的规则，不然他就会取 basePackages 路径去生成bean
)
/**
 * Import() 给容器【ApplicationContextConfig2 这个配置容器】导入组件，导入之后，bean 的名字是 全类名
 *    BeanImportSelector - 实现了 ImportSelector 接口
 *    BeanImportBeanDefinitionRegistrar - 实现了 ImportBeanDefinitionRegistrar 接口
 */
@Import(value = {ApplicationContextConfig1.class, BeanImportSelector.class, BeanImportBeanDefinitionRegistrar.class})
@Configuration
public class ApplicationContextConfig2 {

    //windows 环境注册
    @Conditional(WindowConditional.class)
    @Bean("person-windows")
    public Person buildPerson01() {
        return new Person("Bill Gates", 63);
    }

    //linux 环境注册
    @Conditional(LinuxConditional.class)
    @Bean("person-linux")
    public Person buildPerson02() {
        return new Person("linus", 49);
    }


    /**
     * 	 使用Spring提供的 FactoryBean（工厂Bean）;
     * 		1）、默认获取到的是工厂bean调用getObject创建的对象
     * 		2）、要获取工厂Bean本身，我们需要给id前面加一个&
     * 			&colorFactoryBean
     *
     */
    @Bean
    public HackerFactoryBean buildHackerFactoryBean() {
        return new HackerFactoryBean();
    }
}
