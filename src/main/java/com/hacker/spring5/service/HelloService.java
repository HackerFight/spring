package com.hacker.spring5.service;

import com.hacker.spring5.dao.HelloDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Hacker
 * @date：2018/12/24
 * @project spring
 * @describe
 */
@Service
public class HelloService {

    @Resource
    private HelloDao helloDao123;

    public String sayHello() {
        System.out.println("使用 @Resource 来装配bean >>> " + helloDao123.getClass());
        return "Hello Autowired....in service";
    }

    public HelloService() {
        System.out.println("-----------");
    }
}
