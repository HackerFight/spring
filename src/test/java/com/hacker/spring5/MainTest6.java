package com.hacker.spring5;

import com.hacker.spring5.config.ApplicationContextConfig6;
import com.hacker.spring5.processor.MyBeanProcessor;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author Hacker
 * @date：2018/12/24
 * @project spring
 * @describe
 */
public class MainTest6 {

    AnnotationConfigApplicationContext ctx;

    {
        ctx = new AnnotationConfigApplicationContext(ApplicationContextConfig6.class);
    }

    @Test
    public void testBeanPostProcessor(){
       ctx.getBean(MyBeanProcessor.class);
    }
}
