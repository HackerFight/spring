package com.hacker.spring5;

import com.hacker.spring5.config.ApplicationContextConfig8Transaction;
import com.hacker.spring5.transaction.TransactionService;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author hacker
 * @date 2019/1/8
 * @describe
 */
public class MainTestTransaction {

    AnnotationConfigApplicationContext ctx;

    {
        ctx = new AnnotationConfigApplicationContext(ApplicationContextConfig8Transaction.class);
    }

    /**
     * 这个方法就是看一下 事务的体现
     */
    @Test
    public void testTransaction() {
        TransactionService bean = ctx.getBean(TransactionService.class);
//        bean.addObject();

        try {
            bean.addObjectThrowsException();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
