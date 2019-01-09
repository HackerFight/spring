package com.hacker.spring5.mapper;

import com.hacker.spring5.transaction.ClassEntry;

/**
 * @author hacker
 * @date 2019/1/8
 * @describe
 *
 *  班级的mapper 接口
 */
public interface ClassMapper {

    /**
     * 添加班级
     * @param classEntry
     * @return
     */
    Integer insertClass(ClassEntry classEntry);

    /**
     * 更新班级
     * @param classEntry
     * @return
     */
    Integer updateClass(ClassEntry classEntry);

    /**
     * 删除班级
     * @param classNo
     * @return
     */
    Integer deleteClass(String classNo);


}
