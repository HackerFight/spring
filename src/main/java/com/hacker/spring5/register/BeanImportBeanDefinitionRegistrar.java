package com.hacker.spring5.register;

import com.hacker.spring5.otherbean.Room;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
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

        /**
         * 可以把Spring IoC容器比作一间餐馆，当你来到餐馆，通常会直接招呼服务员：点菜！至于菜的原料是什么？如何用原料把菜做出来？可能你根本就不关心。
         * IoC容器也是一样，你只需要告诉它需要某个bean，它就把对应的实例（instance）扔给你，至于这个bean是否依赖其他组件，怎样完成它的初始化，根本就不需要你关心。
         *
         * 作为餐馆，想要做出菜肴，得知道菜的原料和菜谱，同样地，
         * IoC容器想要管理各个业务对象以及它们之间的依赖关系，需要通过某种途径来记录和管理这些信息。
         * BeanDefinition对象就承担了这个责任：容器中的每一个bean都会有一个对应的BeanDefinition实例，
         * 该实例负责保存bean对象的所有必要信息，包括bean对象的class类型、是否是抽象类、构造方法和参数、其它属性等等
         */
        BeanDefinition beanDefinition = new RootBeanDefinition(Room.class); //注册一个bean

        beanDefinition.setLazyInit(true);

        beanDefinition.setPrimary(true);

        beanDefinition.setInitMethodName("initMethod"); //设置初始化方法

        beanDefinition.setScope(ConfigurableListableBeanFactory.SCOPE_SINGLETON); //设置作用域


        /**
         * 原材料已经准备好（把BeanDefinition看着原料），开始做菜吧，等等，你还需要一份菜谱，BeanDefinitionRegistry和BeanFactory就是这份菜谱，
         * BeanDefinitionRegistry抽象出bean的注册逻辑，而BeanFactory则抽象出了bean的管理逻辑，而各个BeanFactory的实现类就具体承担了bean的注册以及管理工作。
         * DefaultListableBeanFactory作为一个比较通用的BeanFactory实现，它同时也实现了BeanDefinitionRegistry接口，因此它就承担了Bean的注册管理工作。
         * 从图中也可以看出，BeanFactory接口中主要包含getBean、containBean、getType、getAliases等管理bean的方法，
         * 而BeanDefinitionRegistry接口则包含registerBeanDefinition、removeBeanDefinition、getBeanDefinition等注册管理BeanDefinition的方法。
         *
         * BeanFactory、BeanDefinitionRegistry关系图（来自：Spring揭秘）
         */
        registry.registerBeanDefinition("room", beanDefinition);
    }
}
