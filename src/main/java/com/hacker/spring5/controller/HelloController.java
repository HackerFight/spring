package com.hacker.spring5.controller;

import com.hacker.spring5.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Controller;

/**
 * @author Hacker
 * @date：2018/12/21
 * @project spring
 * @describe
 */
@Controller
public class HelloController {

    @Autowired
    //@Qualifier(value = "helloService1")
    private HelloService service;

    public void say() {
        System.out.println(service.sayHello());
    }
}
