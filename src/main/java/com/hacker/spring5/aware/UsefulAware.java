package com.hacker.spring5.aware;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author Hacker
 * @date：2018/12/24
 * @project spring
 * @describe
 *
 * <pre>
 * 自定义组件想要使用Spring容器底层的一些组件（ApplicationContext，BeanFactory，xxx）；
 *        自定义组件实现xxxAware；在创建对象的时候，会调用接口规定的方法注入相关组件；Aware；
 *        把Spring底层一些组件注入到自定义的Bean中；
 *        xxxAware：功能使用xxxProcessor来处理；
 *        ApplicationContextAware ==》 ApplicationContextAwareProcessor；

      Aware 的接口
         ApplicationContextAware           -->获取 ApplicationContext
         ApplicationEventPublisherAware       -->获取 ApplicationEventPublisher  事件派发器
         BeanClassLoaderAware            -->获取 ClassLoader    类加载器
         BeanFactoryAware               -->获取 BeanFactory   bean工厂
         BeanNameAware                -->获取 beanName
         EmbeddedValueResolverAware         -->获取 值解析器,解析占位符
         EnvironmentAware              -->获取 Environment  运行环境
         ImportAware                  -->获取 AnnotationMetadata   导入相关bean
         ResourceLoaderAware             -->获取 ResourceLoader   资源加载
         MessageSourceAware             -->获取 MessageSource    国际化

   </pre>
 */
public class UsefulAware implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
    //....
}
