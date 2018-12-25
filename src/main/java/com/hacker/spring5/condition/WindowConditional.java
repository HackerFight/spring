package com.hacker.spring5.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @author Hacker
 * @date：2018/12/24
 * @project spring
 * @describe  windows 环境
 */
public class WindowConditional implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        System.out.println("metadata >>>> " + metadata);
        String property = context.getEnvironment().getProperty("os.name");
        if (property.contains("Windows")) {
            return true;
        }
        return false;
    }
}
