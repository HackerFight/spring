package com.hacker.spring5;

import com.alibaba.druid.pool.DruidDataSource;
import com.hacker.spring5.bean.MySqlSessionFactory;
import com.hacker.spring5.config.ApplicationContextConfig5;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.sql.DataSource;

/**
 * @author Hacker
 * @date：2018/12/24
 * @project spring
 * @describe
 */
public class MainTest5 {

    /**
     * 如何 测试 Profile
     *    1.JVM参数：-Dspring.profiles.active=test
     *    2.代码，如下
     */
    @Test
    public void testProfile(){
        //1.构建空参容器类
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        /**
         * 2.指定激活的环境； 注意，一旦标注了 @Profile 注解，那么一旦没有匹配的 profile 就会报错，默认他是取的 default 环境
         */
        ctx.getEnvironment().setActiveProfiles("dev");
        //3.注册配置类
        ctx.register(ApplicationContextConfig5.class);
        //4.刷新容器,刷新才是容器真正开始的地方
        ctx.refresh();

        DruidDataSource bean = ctx.getBean(DruidDataSource.class);
        String url = bean.getUrl();
        System.out.println(url);


        MySqlSessionFactory factory = ctx.getBean(MySqlSessionFactory.class);
        String url2 = factory.getDruidDataSource().getUrl();
        System.out.println(url2);

        System.out.println(url == url2);

    }

}
