package com.hacker.spring5.config;

import com.hacker.spring5.service.HelloService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * @author Hacker
 * @date：2018/12/24
 * @project spring
 * @describe
 *    测试依赖注入
 *      1.@Autowired
 *          > 优先按照类型去容器中找对应的组件(ctx.getBean(HelloService.class)),找到就赋值
 *          > 如果找到了多个,那么就以 属性名作为 bean 的id 去容器中查找，ctx.getBean("属性名")，找到就赋值
 *          > 如果此时找不到属性名对应的 bean 的 id， 那么就会抛出异常：NoUniqueBeanDefinitionException
 *      2.@Autowired & @Qualifier
 *          > 通过 @Autowired 指定类型，通过 @Qualifier 来指定民名称，这样就可以确定唯一的一个依赖bean
 *      3.@Primary 优先加载
 *
 *      4.@Resource （他是J2EE[JSR250] 的注解，非spring的）
 *          @Resource装配顺序：
            ①如果同时指定了name和type，则从Spring上下文中找到唯一匹配的bean进行装配，找不到则抛出异常。
            ②如果指定了name，则从上下文中查找名称（id）匹配的bean进行装配，找不到则抛出异常。
            ③如果指定了type，则从上下文中找到类似匹配的唯一bean进行装配，找不到或是找到多个，都会抛出异常。
            ④如果既没有指定name，又没有指定type，则自动按照byName方式进行装配；如果没有匹配，则回退为一个原始类型进行匹配，如果匹配则自动装配。

        5.@inject [JSR330]
            使用它需要去仓库下载依赖
                <dependency>
                <groupId>javax.inject</groupId>
                <artifactId>javax.inject</artifactId>
                <version>1</version>
                </dependency>
            他和 @Autowired 一样，支持@Primary
 */
@Configuration
@ComponentScan(value = {"com.hacker.spring5.controller", "com.hacker.spring5.service", "com.hacker.spring5.dao"})
public class ApplicationContextConfig4 {

    /**
     *   service 层通过 @Bean 创建
     */
    @Bean("helloService1")
    @Primary
    public HelloService buildService() {
        return new HelloService();
    }

}
