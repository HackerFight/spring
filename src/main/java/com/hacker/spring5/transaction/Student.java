package com.hacker.spring5.transaction;

import lombok.Data;

/**
 * @author hacker
 * @date 2019/1/8
 * @describe
 */
@Data
public class Student {

    private Integer id;

    private String name;

    private Integer age;

    private String stuNo;

    private String classNo;


    public Student(String name, Integer age, String stuNo, String classNo) {
        this.name = name;
        this.age = age;
        this.stuNo = stuNo;
        this.classNo = classNo;
    }

}
