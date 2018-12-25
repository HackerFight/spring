package com.hacker.spring5.bean;

/**
 * @author Hacker
 * @date：2018/12/21
 * @project spring
 * @describe
 */
public class Person {

    private String name;

    private Integer age;

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
