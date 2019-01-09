package com.hacker.spring5.isolation;

import com.alibaba.fastjson.JSONArray;
import com.hacker.spring5.config.ApplicationContextConfig8Transaction;
import com.hacker.spring5.transaction.isolation.CTransactionRepeatableRead;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Map;

/**
 * @author hacker
 * @date 2019/1/9
 * @describe
 */
public class RepeatableReadTest2 {

    AnnotationConfigApplicationContext ctx;

    private CTransactionRepeatableRead cTransactionRepeatableRead;

    {
        ctx = new AnnotationConfigApplicationContext(ApplicationContextConfig8Transaction.class);
        cTransactionRepeatableRead = ctx.getBean(CTransactionRepeatableRead.class);
    }

    /**
     * 测试可重复读-插入一条数据
     *  先执行查询事务，在执行 insert 事务
     */
    @Test
    public void testRepeatableReadInsert() {
        long i = cTransactionRepeatableRead.insertUser("javascript");
        System.out.println(i);
    }

    @Test
    public void testRepeatableReadQuery() {
        List<Map<String, Object>> repeatableRead = cTransactionRepeatableRead.getRepeatableRead();
        /**
         * <pre>
         *    [
         *    {"id":1,"user_id":1,"name":"java","login_id":"1001091181","login_password":"abc"},
         *    {"id":1,"user_id":1,"name":"java","login_id":"1001091181","login_password":"abc"}
         *    ]
         *    两次查询结果一致，都是 java, 没有javascript, 而数据库已经插入了 javascript 这条数据
         *
         *    理论上他是符合幻读的要求的，但是为什么第二次没有查询到 javascript 这条数据呢？ 难道
         *    REPEATABLE_READ 已经解决了幻读？
         *
         *    继续请看测试 RepeatableReadTest3
         * </pre>
         */
        System.out.println(JSONArray.toJSONString(repeatableRead));
    }
}
