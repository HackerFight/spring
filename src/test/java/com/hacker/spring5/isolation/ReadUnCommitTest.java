package com.hacker.spring5.isolation;

import com.alibaba.fastjson.JSON;
import com.hacker.spring5.config.ApplicationContextConfig8Transaction;
import com.hacker.spring5.transaction.AdminUserEntity;
import com.hacker.spring5.transaction.isolation.ATransactionReadUnCommit;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author hacker
 * @date 2019/1/8
 * @describe 测试事务的隔离级别效果
 */
public class ReadUnCommitTest {

    AnnotationConfigApplicationContext ctx;

    private ATransactionReadUnCommit ATransactionReadUnCommit;

    {
        ctx = new AnnotationConfigApplicationContext(ApplicationContextConfig8Transaction.class);
        ATransactionReadUnCommit = ctx.getBean(ATransactionReadUnCommit.class);
    }


    /**
     * 测试读未提交:
     * 更新后先让线程sleep 10秒钟，暂不提交update事务，然后我们在期间进行select查询，预期效果为可以查询到这个未提交的事务修改的数据。
     */
    @Test
    public void testReadUnCommitUpdate() {
        //id   user_id      name    login_id     login_password
        //1	   1	        python	1001091181	 abc
        int i = ATransactionReadUnCommit.update(1L, "c++");
        System.out.println(i);
    }

    @Test
    public void testReadUnCommitQuery() {
        AdminUserEntity dataUnCommit = ATransactionReadUnCommit.getDataUnCommit(1L);
        /**
         * 在上面的方法还没有提交的提交的情况下：我这里就查询到了 修改后的值
         * {"id":1,"loginId":"1001091181","loginPassword":"abc","name":"c++","userId":1}
         */
        System.out.println(JSON.toJSONString(dataUnCommit));
    }
}
