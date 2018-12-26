package com.hacker.spring5;

import com.hacker.spring5.aop.MathCalculator;
import com.hacker.spring5.config.ApplicationContextConfig7Aop;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainTest7Aop {

    AnnotationConfigApplicationContext ctx;

    {
        ctx = new AnnotationConfigApplicationContext(ApplicationContextConfig7Aop.class);
    }

    @Test
    public void testAop() {
        MathCalculator bean = ctx.getBean(MathCalculator.class);
        int div = bean.div(10, 0);
        System.out.println(div);
    }
}
