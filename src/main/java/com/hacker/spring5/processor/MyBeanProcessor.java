package com.hacker.spring5.processor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.lang.Nullable;

/**
 * @author Hacker
 * @date：2018/12/24
 * @project spring
 * @describe BeanPostProcessor 这个接口有来两个方法，分别在 初始化完成前和完成后执行：
 * postProcessBeforeInitialization： 初始化前执行
 * postProcessAfterInitialization：初始化完成之后执行
 * <p>
 * 在源码的：AbstractAutowireCapableBeanFactory 的 createBean 的方法中
 */
public class MyBeanProcessor implements BeanPostProcessor {

    public MyBeanProcessor() {
        System.out.println("初始化开始执行........");
    }

    @Nullable
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("postProcessBeforeInitialization >>> " + beanName);
        return bean;
    }

    @Nullable
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("postProcessAfterInitialization >>> " + beanName);
        return bean;
    }
}
