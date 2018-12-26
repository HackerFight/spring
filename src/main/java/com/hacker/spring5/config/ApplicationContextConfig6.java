package com.hacker.spring5.config;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 测试发现：通过 @Component 和 @ComponentScan 两个注解结合起来使用，测试后置处理器生效
 * 而使用 @Bean 注解 创建的 就不行....
 *
 *  具体这里还要看源码执行机制.....TODO....
 */
@Configuration
@ComponentScan(value = "com.hacker.spring5.processor")
public class ApplicationContextConfig6 {

//    @Bean
//    public MyBeanPostProcessor buildBeanPostProcessor() {
//        return new MyBeanPostProcessor();
//    }

}
