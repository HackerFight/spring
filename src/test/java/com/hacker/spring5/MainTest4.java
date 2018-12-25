package com.hacker.spring5;

import com.hacker.spring5.config.ApplicationContextConfig3;
import com.hacker.spring5.config.ApplicationContextConfig4;
import com.hacker.spring5.controller.HelloController;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

/**
 * @author Hacker
 * @date：2018/12/24
 * @project spring
 * @describe
 */
public class MainTest4 {

    private AnnotationConfigApplicationContext ctx;

    private HelloController helloController;

    {
        ctx = new AnnotationConfigApplicationContext(ApplicationContextConfig4.class);
        helloController = ctx.getBean(HelloController.class);
    }

    @Test
    public void testAutowired(){
        helloController.say();
    }

    @Test
    public void testAllBeanNames(){
        String[] beanDefinitionNames = ctx.getBeanDefinitionNames();
        System.out.println(Arrays.toString(beanDefinitionNames));
    }
}
