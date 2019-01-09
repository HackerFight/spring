package com.hacker.spring5.mapper;

import com.hacker.spring5.transaction.Student;

import java.util.List;

/**
 * @author hacker
 * @date 2019/1/8
 * @describe
 *   学生的mapper 接口
 */
public interface StudentMapper {

    /**
     * 添加学生
     * @param student
     */
    Integer insertStudent(Student student);

    /**
     * 修改学生
     * @param student
     */
    Integer  updateStudent(Student student);

    /**
     * 删除学生
     * @param stuNo
     * @return
     */
    Integer deleteStudent(String stuNo);

    /**
     * 获取学生
     * @param stuNo
     * @return
     */
    Student getStudent(String stuNo);

    /**
     * 批量查询学生
     * @param classNo
     * @return
     */
    List<Student> batchQueryStudents(String classNo);
}
