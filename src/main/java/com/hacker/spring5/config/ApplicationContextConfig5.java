package com.hacker.spring5.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.hacker.spring5.bean.MySqlSessionFactory;
import com.hacker.spring5.config.configuration.DataSourceConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.util.StringValueResolver;

import javax.sql.DataSource;

/**
 * @author Hacker
 * @date：2018/12/24
 * @project spring
 * @describe
 * @Profile: Spring 为我们提供的，可以根据当前环境，动态的激活和切换一系列组件的功能（开发环境 / 测试环境 / 生产环境）
 * @Profile： 指定组件在哪个环境的情况下才能被注册到容器中，不指定，任何环境下都能注册这个组件
 * 1）、加了环境标识的bean，只有这个环境被激活的时候才能注册到容器中。默认是default环境
 * 2）、写在配置类上，只有是指定的环境的时候，整个配置类里面的所有配置才能开始生效
 * 3）、没有标注环境标识的bean在，任何环境下都是加载的；
 *
 *
 * 这里取 jdbc.properties 属性文件中的值有一下 2 种方式：
 * 1）通过 @Value 取值
 * 2）实现 EmbeddedValueResolverAware 占位符解析器
 *
 * 问题：在xml 中可以使用 <import></import> 标签，引入其他的xml文件，那注解版的怎么弄呢？
 *     其实是一样的，我将内容提取出来，放到 DataSourceConfig 类中，然后标注 @Configuration
 */

@Configuration
@PropertySource(value = {"classpath:/jdbc.properties"})
@Import(DataSourceConfig.class)
public class ApplicationContextConfig5 {

    @Bean
    public MySqlSessionFactory buildSqlSessionFactory(DruidDataSource druidDataSource) {
         return new MySqlSessionFactory(druidDataSource);
    }
}
