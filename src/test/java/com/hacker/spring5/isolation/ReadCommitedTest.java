package com.hacker.spring5.isolation;

import com.alibaba.fastjson.JSONArray;
import com.hacker.spring5.config.ApplicationContextConfig8Transaction;
import com.hacker.spring5.transaction.isolation.BTransactionReadCommited;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Map;

/**
 * @author hacker
 * @date 2019/1/8
 * @describe 测试事务的隔离级别效果- 读已提交
 */
public class ReadCommitedTest {

    AnnotationConfigApplicationContext ctx;

    private BTransactionReadCommited bTransactionReadCommited;

    {
        ctx = new AnnotationConfigApplicationContext(ApplicationContextConfig8Transaction.class);
        bTransactionReadCommited = ctx.getBean(BTransactionReadCommited.class);
    }

    @Test
    public void testReadCommitUpdate() {
        //id   user_id      name    login_id     login_password
        //1	   1	        hacker	1001091181	 abc
        Long userId = 1L;
        int i = bTransactionReadCommited.update(userId, "c++");
        System.out.println(i);
    }

    /**
     * <pre>
     *  现在数据库中user_id为1的这条数据name是"python"，
     *
     *  不执行上面的update 方法，直接执行这个查询方法：
     *  [
     *    {"id":1,"user_id":1,"name":"python","login_id":"1001091181","login_password":"abc"},
     *    {"id":1,"user_id":1,"name":"python","login_id":"1001091181","login_password":"abc"}
     *  ]
     *  是两条一样的记录，name 都是 python
     *
     *  然后我们执行上面的update操作：testReadCommitUpdate，update事务开启、线程睡眠，
     *
     *  此时执行查询操作：testReadCommitQuery：它内部查询了2次，一次是修改未提交前，一次是修改提交后
     *
     *  [
     *    {"id":1,"user_id":1,"name":"python","login_id":"1001091181","login_password":"abc"},
     *    {"id":1,"user_id":1,"name":"c++","login_id":"1001091181","login_password":"abc"}
     *  ]
     *  此时查到了刚刚已经提交的修改产生的新数据(name为"c++")，但是，出现了不可重复读的问题（同一个事务两次一样的查询出现了不同的结果）。
     *  </pre>
     */
    @Test
    public void testReadCommitQuery() {
        List<Map<String, Object>> dataCommitted = bTransactionReadCommited.getDataCommitted();
        System.out.println(JSONArray.toJSONString(dataCommitted));
    }
}
