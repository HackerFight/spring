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
public class RepeatableReadTest {

    AnnotationConfigApplicationContext ctx;

    private CTransactionRepeatableRead cTransactionRepeatableRead;

    {
        ctx = new AnnotationConfigApplicationContext(ApplicationContextConfig8Transaction.class);
        cTransactionRepeatableRead = ctx.getBean(CTransactionRepeatableRead.class);
    }

    /**
     * 测试可重复读
     *  现在数据库中user_id为 1 的这条数据name是"c++"，
     *  然后我们执行update事务， 事务开启、线程睡眠
     *  此时执行 查询事物
     */
    @Test
    public void testRepeatableReadUpdate() {
        //id   user_id      name    login_id     login_password
        //1	   1	        c++  	1001091181	 abc
        int i = cTransactionRepeatableRead.update(1L, "java");
        System.out.println(i);
    }

    @Test
    public void testRepeatableReadQuery() {
        List<Map<String, Object>> repeatableRead = cTransactionRepeatableRead.getRepeatableRead();
        /**
         * <pre>
         *  第一次查询到name为"c++"，没有读取到未提交的数据。
         *  当update事务提交，进行第二次查询，读取到的数据还是"c++"，但是此时数据库中的name已经为"java"了。
         * [
         *  {"id":1,"user_id":1,"name":"c++","login_id":"1001091181","login_password":"abc"},
         *  {"id":1,"user_id":1,"name":"c++","login_id":"1001091181","login_password":"abc"}
         * ]
         *
         * 调整执行顺序：先执行查询事务，在执行 update 事务，发现结果一致，都是c++
         * </pre>
         */
        System.out.println(JSONArray.toJSONString(repeatableRead));
    }
}
