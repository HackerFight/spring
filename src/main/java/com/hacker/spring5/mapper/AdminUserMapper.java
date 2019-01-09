package com.hacker.spring5.mapper;

import com.hacker.spring5.transaction.AdminUserEntity;

/**
 * @author hacker
 * @date 2019/1/8
 * @describe
 */

public interface AdminUserMapper {

    int updateById(AdminUserEntity adminUserEntity);

    AdminUserEntity selectById(long userId);

    int insertUser(AdminUserEntity adminUserEntity);

    int updateName(AdminUserEntity adminUserEntity);
}
