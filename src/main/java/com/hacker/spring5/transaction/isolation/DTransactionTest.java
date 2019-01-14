package com.hacker.spring5.transaction.isolation;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author hacker
 * @date 2019/1/14
 * @describe
 */
@Component
public class DTransactionTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void updateAndSelect() {

        int update = jdbcTemplate.update("update t_admin_user SET name = 'Whhh' WHERE user_id = 4");
        System.out.println("update: " + update);

        List<Map<String, Object>> maps = jdbcTemplate.queryForList("SELECT * FROM t_admin_user where user_id = 4");
        System.out.println(JSON.toJSONString(maps.get(0)));
    }
}
