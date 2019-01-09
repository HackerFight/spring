package com.hacker.spring5.isolation;

import com.hacker.spring5.config.ApplicationContextConfig8Transaction;
import com.hacker.spring5.transaction.isolation.CTransactionRepeatableRead;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author hacker
 * @date 2019/1/9
 * @describe
 */
public class RepeatableReadTest3 {

    AnnotationConfigApplicationContext ctx;

    private CTransactionRepeatableRead cTransactionRepeatableRead;

    {
        ctx = new AnnotationConfigApplicationContext(ApplicationContextConfig8Transaction.class);
        cTransactionRepeatableRead = ctx.getBean(CTransactionRepeatableRead.class);
    }

    /**
     * 测试可重复读-插入一条数据
     *  先执行查询事务[testRepeatableReadSelectAndUpdate]，在执行 insert 事务
     *
     *  现在数据库还是一条数据，name = c++
     */
    @Test
    public void testRepeatableReadInsert() {
        long i = cTransactionRepeatableRead.insertUser("nodeJs");
        System.out.println(i);
    }

    @Test
    public void testRepeatableReadSelectAndUpdate() {
        int count = cTransactionRepeatableRead.selectAndUpdate(".net");
        /**
         * <pre>
         *   可以看到，第一次查询到的数据量为1，然后等待insert结束，selectAndUpdate方法进行全表update，
         *   更新到的行数为2，第二次查询出来的全表数量也为2。
         *
         *   如果我们把selectAndUpdate方法中的全表更新放在sleep之前，也就是说，先开启A【查询】事务、查询数量、
         *   全表更新name字段，B【insert事务】事务插入、b事务结束，A事务再次查询、A事务结束。
         *   具体请看 RepeatableReadTest4
         * </pre>
         */
        System.out.println("count : " + count);
    }
}
