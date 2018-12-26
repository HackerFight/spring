package com.hacker.spring5.test;

import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

public class TypeTest {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(ApplicationContextConfigTest.class);

        ConfigurableListableBeanFactory beanFactory = ctx.getBeanFactory();

        String[] beanNamesForType = beanFactory.getBeanNamesForType(BeanPostProcessor.class);

        /**
         * 可以看到 我定义的  AnnotationAwareAspectJAutoProxyBean 类可以打印出来
         *  我这里测试的目的就是为了验证源码：
         *     AbstractApplicationContext:
         *        registerBeanPostProcessors(beanFactory);
         *
         *     PostProcessorRegistrationDelegate.registerBeanPostProcessors(beanFactory, this);
         *
         *     String[] postProcessorNames = beanFactory.getBeanNamesForType(BeanPostProcessor.class, true, false);
         *
         *     因为 AOP 中，AnnotationAwareAspectJAutoProxyCreator 最后的继承关系中是实现了 SmartInstantiationAwareBeanPostProcessor 这个处理器接口
         *
         */
        System.out.println(Arrays.toString(beanNamesForType));
    }
}
