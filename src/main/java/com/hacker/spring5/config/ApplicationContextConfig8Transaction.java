package com.hacker.spring5.config;

import com.hacker.spring5.config.configuration.mapperconfig.MapperConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * @author hacker
 * @date 2019/1/7
 * @describe <pre>
 *  1.声明式事物
 *      1）环境准备
 *          数据源  、 驱动  、spring-jdbc 模块
 *      2) 配置数据源，JdbcTemplate(Spring提供的一个简化数据库操作的一个工具)
 *      3) 给方法标注 @Transactional 表示当前方法是一个事物方法
 *      4) 在配置类中标注  @EnableTransactionManagement 开启基于注解的事物管理功能
 *      5) 给IOC容器配置事物管理器：
 *       @Bean public PlatformTransactionManager transactionManager() {
 *           return new DataSourceTransactionManager(dataSource()); //数据源我已经配置好了
 *       }
 * <p>
 *   2.原理分析 - @EnableTransactionManagement (需要引入 spring-jdbc 的maven 模块)
 *       1) 利用@Import() 给容器注入 TransactionManagementConfigurationSelector（实现了 ImportSelector 接口）
 *          通过他【selectImports(AdviceMode adviceMode)】给容器导入两个组件：（EnableTransactionManagement 注解的 mode 默认是 PROXY)
 *           AutoProxyRegistrar
 *           ProxyTransactionManagementConfiguration
 *
 *           这个 adviceMode 是怎么传入的呢？他是在本类的父类中调用 selectImports() 方法内部调用的这个实现方法
 *
 *       2) AutoProxyRegistrar: 给容器注册 InfrastructureAdvisorAutoProxyCreator，他是一个
 *           SmartInstantiationAwareBeanPostProcessor 类型的后置处理器；利用后置处理器在对象创建以后
 *           包装对象，返回一个代理对象(增强器)，代理对象执行目标方法利用拦截器链进行拦截
 *
 *       3) ProxyTransactionManagementConfiguration 做了什么?（他本身是一个配置类)
 *           ① 给容器注册事物增强器(BeanFactoryTransactionAttributeSourceAdvisor),这个增强器需设置两个东西：
 *              1）事物的注解信息，AnnotationTransactionAttributeSource 解析事物的注解
 *              2）事物的拦截器： TransactionInterceptor，保存了事物属性信息和 事物管理器，注意他是 MethodInterceptor
 * <p>
 *           TransactionInterceptor 他又做了什么?
 *           a.获取事物的相关属性
 *           b.在获取 PlatformTransactionManager，如果事先没有指定任何的 transactionManager(他是 @Transactional注解的一个属性)
 *             最终会按照类型从IOC容器中获取一个 PlatformTransactionManager
 *           c.执行目标方法
 *              如果正常：利用事物管理器，提交事物
 *              如果异常：利用事物管理器，回滚
 *
 *         TODO.... 这部分源码我并没有深入的分析和研读，在理解上还不到位
 * <p>
 * </pre>
 */
@Configuration
@EnableTransactionManagement
@Import(MapperConfig.class)
@ComponentScan(value = "com.hacker.spring5.transaction")
public class ApplicationContextConfig8Transaction {

    /**
     * 这个 dataSource 他会直接使用 MapperConfig 里面创建的数据源
     */
    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}
