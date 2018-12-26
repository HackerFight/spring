package com.hacker.spring5.aop;

import org.springframework.stereotype.Component;

/**
 * 这是一个业务逻辑组件，添加 @Component 注解
 */
@Component
public class MathCalculator {

    public int div(int i, int j) {
        return i / j;
    }
}
