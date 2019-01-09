package com.hacker.spring5.config.configuration.mapperconfig;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * @author hacker
 * @date 2019/1/7
 * @describe xml 配置方式：
 * <pre>
 *   <!--asset数据源-->
 *      <bean id="asset" class="org.apache.commons.dbcp2.BasicDataSource">
 *           <property name="username" value="${mysql.username}"/>
 *           <property name="password" value="${mysql.password}"/>
 *           <property name="driverClassName" value="${mysql.driverClassName}"/>
 *           <property name="url" value="${mysql.asset.url}"/>
 *      </bean>
 *
 *      <bean id="assetSqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
 *           <property name="dataSource" ref="asset"/>
 *           <property name="configLocation" value="classpath:mapper/configuration/mybatis-configuration.xml" />
 *           <property name="mapperLocations" value="classpath:mapper/*.xml"></property>
 *      </bean>
 *
 *      <bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
 *           <property name="basePackage" value="com.ioe.energyManagement.dao"/>
 *           <property name="sqlSessionFactoryBeanName" value="assetSqlSessionFactory"></property>
 *      </bean>
 *
 *       <bean id="asseTransactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
 *           <property name="dataSource" ref="asset"/>
 *       </bean>
 *
 *       <tx:annotation-driven transaction-manager="asseTransactionManager"/>
 *
 * </pre>
 */

@Configuration
//制定扫描哪些接口，并生成对应的代理类对象
@MapperScan(value = "com.hacker.spring5.mapper")
//加载数据源配置文件
@PropertySource(value = "classpath:jdbc.properties")
public class MapperConfig {

    @Value("${jdbc.username}")
    private String username;

    @Value("${jdbc.password}")
    private String password;

    @Value("${jdbc.url}")
    private String url;

    @Value("${jdbc.driverClass}")
    private String driverClassName;

    /**
     * 这种方式也可以加载属性文件
     */
//    @Bean
//    public PropertyPlaceholderConfigurer loadProperties() {
//        PropertyPlaceholderConfigurer configurer = new PropertyPlaceholderConfigurer();
//        configurer.setLocation(new ClassPathResource("jdbc.properties"));
//        return configurer;
//    }


    /**
     * 这种方式也可以指定 mapper 接口的路径
     */
//      @Bean
//      public MapperScannerConfigurer mapperScannerConfigurer() {
//          MapperScannerConfigurer configurer = new MapperScannerConfigurer();
//          configurer.setBasePackage("com.hacker.spring5.mapper");
//          return configurer;
//      }

    /**
     * 1.配置数据源
     */
    @Bean
    public DruidDataSource dataSource() {
        System.out.println("我创建了几次.......");
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setUrl(url);
        return dataSource;
    }

    /**
     * 2.不要忘记他， 注册一个平台的事物管理器
     */
    @Bean
    public PlatformTransactionManager platformTransactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

    /**
     * 3.配置SqlSessionFactory
     */
    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource()); //数据源
        /**
         *  指定 xxxMapper.xml 的位置，sql 语句还是写到 xml 中去
         *  注意：不要写 classpath
         *        好像也不能写通配符 *
         */
        Resource[] resources = { new ClassPathResource("mapper/ClassMapper.xml"), new ClassPathResource("mapper/StudentMapper.xml"),
                                 new ClassPathResource("mapper/AdminUserMapper.xml")
                               };
        sqlSessionFactoryBean.setMapperLocations(resources);

        //指定全局配置文件
        //sqlSessionFactoryBean.setConfigLocation(new ClassPathResource(""));
        return sqlSessionFactoryBean.getObject();
    }

}
