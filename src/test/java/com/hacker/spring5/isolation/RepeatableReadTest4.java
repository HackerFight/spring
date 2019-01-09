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
public class RepeatableReadTest4 {

    AnnotationConfigApplicationContext ctx;

    private CTransactionRepeatableRead cTransactionRepeatableRead;

    {
        ctx = new AnnotationConfigApplicationContext(ApplicationContextConfig8Transaction.class);
        cTransactionRepeatableRead = ctx.getBean(CTransactionRepeatableRead.class);
    }

    /**
     *
     *  现在数据库还是2条数据，name 都是 .net
     *  先执行查询事务[testRepeatableReadSelectAndUpdate]，在执行 insert 事务
     */
    @Test
    public void testRepeatableReadInsert() {
        long i = cTransactionRepeatableRead.insertUser("nodeJs");
        System.out.println(i);
    }

    @Test
    public void testRepeatableReadSelectAndUpdate() {
        int count = cTransactionRepeatableRead.selectAndUpdate2("Go");
        /**
         * <pre>
         * update事务只修改了2条数据，两次select查询到的数据量也为2，此时数据库中数据量为3：
         *
         * 1	 1	Go	    1001091181	    abc
         * 5	 2	Go	    1547003785514	1547003785514
         * 7	 2	nodeJs	1547004656732	1547004656732
         *
         * 题外话：这里要注意几个时间点，insert方法sleep 10秒钟，但是insert事务开启的时间为：11:02:58，结束的时间为：11:03:27 (30s)
         * 且在selectAndUpdate2事务结束之后10秒钟后才结束，selectAndUpdate2中update的时候全表锁定（因为没有加索引条件），
         * insert方法需等待update的锁释放。
         * </pre>
         */
        System.out.println("count : " + count);
    }
}
