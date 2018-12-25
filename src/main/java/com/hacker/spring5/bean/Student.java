package com.hacker.spring5.bean;

/**
 * @author Hacker
 * @date：2018/12/21
 * @project spring
 * @describe
 */
public class Student {

    private Cat cat;

    private String name;

    private String stuNo;

    public Cat getCat() {
        return cat;
    }

    public void setCat(Cat cat) {
        this.cat = cat;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStuNo() {
        return stuNo;
    }

    public void setStuNo(String stuNo) {
        this.stuNo = stuNo;
    }

    public Student() {
    }

    public Student(String name, String stuNo) {
        this.name = name;
        this.stuNo = stuNo;
    }

    public Student(Cat cat) {
        this.cat = cat;
    }
}
