package com.hacker.spring5.config.configuration;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.util.StringValueResolver;

import javax.sql.DataSource;

/**
 * @author Hacker
 * @date：2018/12/24
 * @project spring
 * @describe
 */
@Configuration
public class DataSourceConfig implements EmbeddedValueResolverAware {

    @Value("${jdbc.driverClass}")
    private String driverClass;

    private String testUserName;

    private String testPassword;

    private String testUrl;

    private String productUserName;

    private String productPassword;

    private String productUrl;

    private StringValueResolver stringValueResolver;


    @Profile(value = "dev")
    @Bean("dataSourceDev")
    public DruidDataSource buildDataSourceDev(@Value("${jdbc.dev.password}") String password,
                                              @Value("${jdbc.dev.username}") String username,
                                              @Value("${jdbc.dev.url}") String url) {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setUrl(url);
        dataSource.setDriverClassName(driverClass);
        return dataSource;
    }

    @Profile(value = "test")
    @Bean("dataSourceTest")
    public DruidDataSource buildDataSourceTest() throws Exception {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setPassword(testPassword);
        dataSource.setUsername(testUserName);
        dataSource.setUrl(testUrl);
        dataSource.setDriverClassName(driverClass);
        return dataSource;
    }

    @Profile(value = "product")
    @Bean("dataSourceProduct")
    public DruidDataSource buildDataSourceProduct() throws Exception {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClass);
        dataSource.setPassword(productPassword);
        dataSource.setUsername(productUserName);
        dataSource.setUrl(productUrl);
        return dataSource;
    }

    @Override
    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        this.stringValueResolver = resolver;
        testUserName = stringValueResolver.resolveStringValue("${jdbc.test.username}");
        testPassword = stringValueResolver.resolveStringValue("${jdbc.test.password}");
        testUrl = stringValueResolver.resolveStringValue("${jdbc.test.url}");
        productUserName = stringValueResolver.resolveStringValue("${jdbc.product.username}");
        productPassword = stringValueResolver.resolveStringValue("${jdbc.product.password}");
        productUrl = stringValueResolver.resolveStringValue("${jdbc.product.url}");
    }
}
