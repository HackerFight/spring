package com.hacker.spring5.factory;

import com.hacker.spring5.otherbean.Color;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.core.ResolvableType;
import org.springframework.lang.Nullable;

import javax.security.auth.login.Configuration;

/**
 * @author Hacker
 * @date：2018/12/21
 * @project spring
 * @describe
 */
public class HackerFactoryBean implements FactoryBean<Color> {

    @Nullable
    @Override
    public Color getObject() throws Exception {
        System.out.println("HackerFactoryBean 的 getObject() 返回一个 Color 的对象");
        return new Color();
    }

    @Nullable
    @Override
    public Class<?> getObjectType() {
        return Color.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
