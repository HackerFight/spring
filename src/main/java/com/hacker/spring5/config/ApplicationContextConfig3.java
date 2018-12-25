package com.hacker.spring5.config;

import com.hacker.spring5.value.PersonBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import sun.awt.SunHints;

/**
 * @author Hacker
 * @date：2018/12/21
 * @project spring
 * @describe
 *
 *    测试 @PropertySource 和 @Value
 */

@Configuration
/**
 * 导入属性配置文件,类似于xml中:
 *    <context:property-placeholder location="classpath*:person.properties"/>
 * 这样就相当于 在 容器中 有了这个属性文件（实际上是在环境变量中，可以通过 Environment  去获取）
 * ignoreResourceNotFound : 默认是 false ，如果没有这个属性文件，就会报错，如果不想报错，可以置为 true
 *
 * @PropertySource 注解是一个重复注解，加载多个配置文件，可以用 , 分开， 或者 使用多个 @PropertySource 注解
 *                   在或者使用 @PropertySources
 */
@PropertySource( value = "classpath:person.properties", ignoreResourceNotFound = true )
//@PropertySources(value = {
//        @PropertySource(value = "classpath:person.properties", ignoreResourceNotFound = true ),
//        @PropertySource(value = "classpath:jdbc.properties")
//})
public class ApplicationContextConfig3 {

    @Bean(value = "person")
    public PersonBean buildPerson() {
        return new PersonBean();
    }
}
