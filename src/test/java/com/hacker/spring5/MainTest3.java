package com.hacker.spring5;
import com.hacker.spring5.config.ApplicationContextConfig3;
import com.hacker.spring5.value.PersonBean;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.Environment;

/**
 * @author Hacker
 * @date：2018/12/24
 * @project spring
 * @describe
 */
public class MainTest3 {

    private AnnotationConfigApplicationContext ctx;

    private Environment environment;

    {
        ctx = new AnnotationConfigApplicationContext(ApplicationContextConfig3.class);
        environment = ctx.getBean(Environment.class);
    }

    @Test
    public void testPropertySourceAndValue() {
        PersonBean bean = ctx.getBean(PersonBean.class);
        System.out.println(bean.getPhone().size());
    }

    /**
     * 通过 @PropertySource 注解加载的属性文件（包括xml的方式）都是保存在环境变量中了，
     *  spring 底层是 保存在了 Environment 这个 接口中了
     */
    @Test
    public void testEnvironment(){
        String username = environment.getProperty("username");
        System.out.println(username); //Hacker
    }
}
