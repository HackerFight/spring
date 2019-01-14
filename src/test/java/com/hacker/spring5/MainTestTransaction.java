package com.hacker.spring5;

import com.hacker.spring5.config.ApplicationContextConfig8Transaction;
import com.hacker.spring5.transaction.TransactionService;
import com.hacker.spring5.transaction.isolation.DTransactionTest;
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


    /**
     * 同一个事物方法中，update 操作了，紧接着去 select，是可以读到update 后的值的
     * 无论是采用哪种隔离级别
     */
    @Test
    public void testUpdateAndSelect() {
        DTransactionTest bean = ctx.getBean(DTransactionTest.class);
        bean.updateAndSelect();
    }

}
