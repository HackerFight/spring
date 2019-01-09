package com.hacker.spring5.transaction.isolation;

import com.hacker.spring5.mapper.AdminUserMapper;
import com.hacker.spring5.transaction.AdminUserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

/**
 * <pre>
 * @author hacker
 * @date 2019/1/8
 * @describe 事务的隔离级别测试
 *
 *   事务隔离级别一共有4种，分别为：READ UNCOMMITTED（读未提交）、READ COMMITTED（读已提交）、REPEATABLE READ（可重复读）、SERIALIZABLE（串行化），
 *   并发随着等级的提高而降低，mysql默认隔离等级为REPEATABLE READ。
 *
 *   READ UNCOMMITTED 会产生脏读、不可重复读、幻读。
 *   READ COMMITTED 解决了脏读，会产生不可重复读、幻读。
 *   REPEATABLE READ 解决了脏读、不可重复读，解决部分幻读问题。（Mysql默认）
 *   SERIALIZABLE 不会产生任何问题，事务没有并发，效率差。
 *
 *   什么是脏读？
 *       其实就是A事务读到了B事务回滚前的脏数据。比如事务B执行过程中修改了数据X，在未提交前，事务A读取了X，而事务B却回滚了，这样事务A就形成了脏读。
 *
 *   什么是不可重复读？
 *       事务A首先读取了一条数据，然后执行逻辑的时候，事务B将这条数据改变了，然后事务A再次读取的时候，发现数据不匹配了，就是所谓的不可重复读了。
 *
 *   什么是幻度？
 *      事务A首先根据条件索引得到N条数据，然后事务B增添了M条符合事务A搜索条件的数据，导致事务A再次搜索发现有N+M条数据了，就产生了幻读。
 *
 *   不可重复读 和 幻读的 区别？
 *      不可重复读和幻读的区别是：前者是指读到了已经提交的事务的【更改数据】（修改或删除），后者是指读到了其他已经提交事务的【新增数据】。
 *      对于这两种问题解决采用不同的办法，防止读到更改数据，只需对操作的数据添加行级锁（行级锁可以吗？不太懂），防止操作中的数据发生变化；而防止读到新增数据，
 *      往往需要添加表级锁，将整张表锁定，防止新增数据（oracle采用多版本数据的方式实现）。
 * </pre>
 */

/**
 * 测试 读未提交 [ READ UNCOMMITTED ]
 *
 *   更新后先让线程sleep 10秒钟，暂不提交update事务，然后我们在期间进行select查询，预期效果为可以查询到这个未提交的事务修改的数据。
 */
@Component
public class ATransactionReadUnCommit {

    private static final Logger logger = LoggerFactory.getLogger(ATransactionReadUnCommit.class);

    @Autowired
    private AdminUserMapper adminUserMapper;

    /**
     * update事务
     */
    @Transactional()
    public int update(Long userId,String name) {
        logger.info("----------  update start ----------");
        AdminUserEntity adminUserEntity = new AdminUserEntity();
        adminUserEntity.setUserId(userId);
        adminUserEntity.setName(name);
        logger.info("set name = '{}'",name);
        int i = adminUserMapper.updateById(adminUserEntity);

        logger.info("update sleeping");
        try {
            /**
             * 睡10s
             */
            Thread.sleep(10 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("----------  update sleep end  ----------");
        return i;
    }

    /**
     *  select事务
     */
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public AdminUserEntity getDataUnCommit(Long userId) {
        AdminUserEntity adminUserEntity = adminUserMapper.selectById(userId);
        logger.info("getData [READ_UNCOMMITTED] => adminUserEntity:{}", adminUserEntity);
        return adminUserEntity;
    }

}
