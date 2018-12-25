package com.hacker.spring5.config;

import com.hacker.spring5.processor.MyBeanProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Hacker
 * @date：2018/12/24
 * @project spring
 * @describe
 */
@Configuration
public class ApplicationContextConfig6 {

    @Bean
    public MyBeanProcessor buildBeanPostProcessor() {
        return new MyBeanProcessor();
    }
}
