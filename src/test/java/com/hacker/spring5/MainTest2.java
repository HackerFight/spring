package com.hacker.spring5;

import com.hacker.spring5.config.ApplicationContextConfig2;
import com.hacker.spring5.factory.HackerFactoryBean;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

/**
 * @author Hacker
 * @date：2018/12/21
 * @project spring
 * @describe
 */
public class MainTest2 {

    private AnnotationConfigApplicationContext ctx;

    {
        ctx = new AnnotationConfigApplicationContext(ApplicationContextConfig2.class);
    }

    @Test
    public void testComponentScan(){
        String[] beanDefinitionNames = ctx.getBeanDefinitionNames();
        System.out.println(Arrays.asList(beanDefinitionNames));
    }

    @Test
    public void testFactoryBean() throws Exception{
        /**
         * 首先这样是无法查看到 工厂bean 的，因为他是通过 工厂来产生的，所以首先要定位到工厂才可以
         */
        String[] beanDefinitionNames = ctx.getBeanDefinitionNames();
        System.out.println(Arrays.asList(beanDefinitionNames));

        /**
         * 这样子是可以获取到 工厂里面的 bean 的，返回的也是 Color 对象
         */
        Object bean1 = ctx.getBean("buildHackerFactoryBean");
        System.out.println(bean1);

        /**
         * 这样获取到就是 HackerFactoryBean 对象，无法获取到工厂里面的 bean 对象
         * 然后通过 getObject() 来获取 里面的 bean 对象，上面是直接就获取了工厂里的 bean 对象
         */
        HackerFactoryBean bean2 = ctx.getBean(HackerFactoryBean.class);
        System.out.println(bean2);

        /**
         * 这样也可以获取工厂对象,前面加一个 & 前缀：
         * @see org.springframework.beans.factory.BeanFactory
         */
        Object bean3 = ctx.getBean("&buildHackerFactoryBean");
        System.out.println(bean3);

    }

    @Test
    public void testCondition(){
        String[] beanDefinitionNames = ctx.getBeanDefinitionNames();
        System.out.println(Arrays.asList(beanDefinitionNames));
    }
}
