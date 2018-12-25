package com.hacker.spring5.bean;
import com.alibaba.druid.pool.DruidDataSource;

import javax.sql.DataSource;

/**
 * @author Hacker
 * @date：2018/12/24
 * @project spring
 * @describe
 */
public class MySqlSessionFactory {

    private DruidDataSource druidDataSource;

    public MySqlSessionFactory(DruidDataSource druidDataSource) {
        this.druidDataSource = druidDataSource;
    }

    public MySqlSessionFactory() {
    }

    public DruidDataSource getDruidDataSource() {
        return druidDataSource;
    }

    public void setDruidDataSource(DruidDataSource druidDataSource) {
        this.druidDataSource = druidDataSource;
    }
}

