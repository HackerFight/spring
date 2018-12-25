package com.hacker.spring5.bean;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author Hacker
 * @date：2018/12/21
 * @project spring
 * @describe
 */
public class Air implements InitializingBean, DisposableBean{

    //init
    public void initColor() {
        System.out.println("天空的颜色是蓝的");
    }

    //destroy
    public void destroyBean() {
        System.out.println("对象被销毁了");
    }


    //销毁，实现 DisposableBean 接口
    @Override
    public void destroy() throws Exception {

    }

    //初始化 实现 InitializeBean 接口
    @Override
    public void afterPropertiesSet() throws Exception {

    }

    //注解版的 初始化
    @PostConstruct
    public void annInit() {

    }

    //注解版的 销毁
    @PreDestroy
    public void annDestroy() {

    }
}
