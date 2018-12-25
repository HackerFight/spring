package com.hacker.spring5;

import com.hacker.spring5.bean.Air;
import com.hacker.spring5.bean.Person;
import com.hacker.spring5.bean.Student;
import com.hacker.spring5.config.ApplicationContextConfig1;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author Hacker
 * @date：2018/12/21
 * @project spring
 * @describe
 */
public class MainTest1 {
    
    private AnnotationConfigApplicationContext  ctx;

    {
        ctx = new AnnotationConfigApplicationContext(ApplicationContextConfig1.class);
    }

    /**
     * 测试 @Configuration @Bean   @Lazy   @Scope
     */
    @Test
    public void testConfigurationAndBean(){
        Person bean = ctx.getBean(Person.class);
        Object personBean = ctx.getBean("personBean");
        System.out.println(bean == personBean);
        System.out.println(personBean);
    }

    /**
     * 测试bean 的依赖
     */
    @Test
    public void testConfigurationAndBeanDependencies(){
        Student bean = ctx.getBean(Student.class);
        System.out.println(bean);
    }

    /**
     * 测试初始化和销毁
     */
    @Test
    public void testInitAndDestroy(){
        //ctx.getBean("air");
        ctx.close(); //关闭容器，调用销毁方法
    }
    
}
