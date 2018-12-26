package com.hacker.spring5.test;


import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(AnnotationAwareAspectJAutoProxyBean.class)
public class ApplicationContextConfigTest {


}
