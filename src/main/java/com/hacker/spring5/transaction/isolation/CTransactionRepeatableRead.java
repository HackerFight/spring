package com.hacker.spring5.transaction.isolation;

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

import com.hacker.spring5.mapper.AdminUserMapper;
import com.hacker.spring5.transaction.AdminUserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 测试 可重复读
 */
@Component
public class CTransactionRepeatableRead {

    private static final Logger logger = LoggerFactory.getLogger(CTransactionRepeatableRead.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private AdminUserMapper adminUserMapper;


    /**
     * 保持 update 事务 和之前一样
     */
    @Transactional()
    public int update(Long userId, String name) {
        System.out.println("----------  update start ----------" + LocalDateTime.now());
        AdminUserEntity adminUserEntity = new AdminUserEntity();
        adminUserEntity.setName(name);
        adminUserEntity.setUserId(userId);
        System.out.println("set name = '{}'" +  name);
        int i = adminUserMapper.updateById(adminUserEntity);
        System.out.println("update sleeping。。。。。。");
        try {
            /**
             * 睡10s
             */
            Thread.sleep(10 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("----------  update end ----------" + LocalDateTime.now());
        return i;
    }

    /**
     * select 事务- 可重复读
     */
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public List<Map<String, Object>> getRepeatableRead() {
        System.out.println("----------  get start ----------" + LocalDateTime.now());
        List<Map<String, Object>> allData = new LinkedList<>();
        List<Map<String, Object>> list = jdbcTemplate.queryForList("select * from t_admin_user");
        System.out.println("getData [REPEATABLE_READ] (1) :");
        for (Map<String, Object> adminUserEntity : list) {
            System.out.println(adminUserEntity.toString());
        }
        try {
            Thread.sleep(1000 * 20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<Map<String, Object>> maps = jdbcTemplate.queryForList("select * from t_admin_user");
        allData.addAll(list); //第一次查询到的结果
        allData.addAll(maps); //第二次查询到的结果
        System.out.println("getData [REPEATABLE_READ] (2) :");
        for (Map<String, Object> adminUserEntity : maps) { // maps
            System.out.println(adminUserEntity.toString());
        }
        System.out.println("----------  get end ----------" + LocalDateTime.now());
        return allData;
    }


    @Transactional()
    public long insertUser(String name) {
        System.out.println("----------  insert start ----------" + LocalDateTime.now());
        long id = System.currentTimeMillis();
        System.out.println("the id is : {} " + id);
        AdminUserEntity adminUserEntity = new AdminUserEntity();
        adminUserEntity.setUserId(2L);
        adminUserEntity.setName(name);
        adminUserEntity.setLoginId(System.currentTimeMillis() + "");
        adminUserEntity.setLoginPassword(System.currentTimeMillis() + "");
        System.out.println("set name = '{}' " + name);
        int i = adminUserMapper.insertUser(adminUserEntity); //一旦udpate 全表更新，就会锁住全表，这里就会出现阻塞
        System.out.println("insert sleeping");
        try {
            Thread.sleep(10 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("----------  insert end ----------" + LocalDateTime.now());
        return id;
    }

    /**
     * 保持insert方法不变，新增一个selectAndUpdate方法：
     */

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public int selectAndUpdate(String name) {
        System.out.println("----------  selectAndUpdate start ----------" + LocalDateTime.now());
        AdminUserEntity adminUserEntity = new AdminUserEntity();
        adminUserEntity.setName(name);
        System.out.println("set name = '{}'" +  name);

        //1.查询全表数量
        Integer size = jdbcTemplate.queryForList("select * from t_admin_user").size();
        System.out.println("size1 :{}" + size);
        try {
            Thread.sleep(20 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //2.全表更新 name字段
        int updateSize = adminUserMapper.updateName(adminUserEntity);
        System.out.println("selectAndUpdateSize :{}"+ updateSize);
        //3.查询全表数量
        List<Map<String, Object>> list = jdbcTemplate.queryForList("select * from t_admin_user");
        System.out.println("size2:{}" + list.size());
        System.out.println("----------  selectAndUpdate sleep end  ----------" + LocalDateTime.now() );
        return list.size();
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public int selectAndUpdate2(String name) {
        System.out.println("----------  selectAndUpdate2 start ----------" + LocalDateTime.now());
        AdminUserEntity adminUserEntity = new AdminUserEntity();
        adminUserEntity.setName(name);
        System.out.println("set name = '{}'" + name);

        //1.查询全表数量
        Integer size = jdbcTemplate.queryForList("select * from t_admin_user").size();
        System.out.println("size1 :{}" +  size);

        //2.全表更新 name字段
        int updateSize = adminUserMapper.updateName(adminUserEntity);
        System.out.println("selectAndUpdateSize :{}" + updateSize);

        //3.睡眠放到 全表更新之后
        try {
            Thread.sleep(20 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //4.查询全表数量
        List<Map<String, Object>> list = jdbcTemplate.queryForList("select * from t_admin_user");
        System.out.println("size2:{}" + list.size());
        System.out.println("----------  selectAndUpdate2 sleep end  ----------"  + LocalDateTime.now() );
        return list.size();

    }
}
