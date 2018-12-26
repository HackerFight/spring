package com.hacker.spring5.aop;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * 1.导入：spring-aspects 就可以了
 * <dependency>
 * <groupId>org.springframework</groupId>
 * <artifactId>spring-aspects</artifactId>
 * <version>5.1.2.RELEASE</version>
 * </dependency>
 * <p>
 * 2.这是一个 切面组件，添加 @Aspect 注解
 * 3.同时 也要放入到 容器中
 */
@Aspect
@Component
@Order(1)
public class LogAspect {

    /**
     * 第一个 * ：任意修饰符任意返回值，如果写两个，会报错
     * 第二个 * ：任意方法
     * 方法 ： (..)代表所有参数,(*)代表一个参数,(*,String)代表第一个参数为任何值,第二个为String类型.
     */
    @Pointcut(value = "execution(* com.hacker.spring5.aop.MathCalculator.*(int,*))")
    public void point() {
    }

    /**
     * 声明该方法是一个前置通知：在目标方法开始之前执行
     */
    //@Before(value = "execution(public int com.hacker.spring5.aop.MathCalculator.*(..))")
    @Before(value = "point()")
    public void logStart(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        String name = joinPoint.getSignature().getName();
        System.out.println(name + " 方法运行......@Before:参数列表是：" + Arrays.asList(args));
    }

    /**
     * 后置通知：在目标方法执行后（无论是否发生异常），执行的通知
     * 在后置通知中，还不能访问目标方法执行的结果
     */
    @After(value = "point()")
    public void logEnd(JoinPoint joinPoint) {
        System.out.println("" + joinPoint.getSignature().getName() + "结束。。。@After");
    }

    /**
     * JoinPoint 一定要出现在参数表的第一位
     * 这里 returning="result" 必须和形参中的名称保持一致
     */
    @AfterReturning(value = "point()", returning = "result")
    public void logReturn(JoinPoint joinPoint, Object result) {
        System.out.println("" + joinPoint.getSignature().getName() + "正常返回。。。@AfterReturning:运行结果：{" + result + "}");
    }


    /**
     * 异常通知， 注意事项 同上
     *
     * @param joinPoint
     * @param ex
     */
    @AfterThrowing(value = "point()", throwing = "ex")
    public void logException(JoinPoint joinPoint, Exception ex) {
        System.out.println("" + joinPoint.getSignature().getName() + "异常。。。异常信息：{" + ex + "}");
    }

    /**
     * 1.环绕通知方法的参数必须是：ProceedingJoinPoint  他是 JoinPoint 的子接口
     * 2.在环绕通知中必须明确调用 ProceedingJoinPoint  的 proceed() 方法 来执行被代理的方法，如果
     * 忘记这样做，就会导致通知方法被执行了，但是目标方法没有被执行
     * 3.注意：环绕方法需要方法 目标方法 执行之后的结果，即调用  proceed() 方法的返回值，否则会出现
     * 空指针异常，也就是说 环绕通知必须有返回值。
     */
    @Around(value = "point()")
    public Object aroundNotice(ProceedingJoinPoint joinPoint) {
        Object result = null;
        //执行目标方法
        try {
            //1.前置通知
            System.out.println("方法 " + joinPoint.getSignature().getName() + " 的参数是： " + Arrays.asList(joinPoint.getArgs()));

            //2.执行目标方法
            result = joinPoint.proceed();

            //3.后置通知
            System.out.println("方法 " + joinPoint.getSignature().getName() + " 执行完毕");
        } catch (Throwable throwable) {
            //4.异常通知
            System.out.println("方法 " + joinPoint.getSignature().getName() + " 发生了异常；" + throwable);
            throwable.printStackTrace();
        }
        //5.返回通知
        System.out.println("方法 " + joinPoint.getSignature().getName() + "执行结果是: " + result);

        return result;
    }
}
