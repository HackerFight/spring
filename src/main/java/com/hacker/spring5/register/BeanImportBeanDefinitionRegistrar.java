package com.hacker.spring5.register;

import com.hacker.spring5.otherbean.Room;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author Hacker
 * @date：2018/12/21
 * @project spring
 * @describe 实现这个接口可以向容器注册组件  然后通过 @Import() 将这个类导入容器
 */
public class BeanImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {

        BeanDefinition beanDefinition = new RootBeanDefinition(Room.class); //注册一个bean
        registry.registerBeanDefinition("room", beanDefinition);
    }
}
