package com.didispace.web;

/**
 * Created by Administrator on 2016/9/16.
 */
public class User {

    private String name;
    private Integer age;

    // 须有User的默认构造函数，否则Spring Cloud Feign根据JSON转User对象的时候会报错
    public User() {}

    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "name=" + name + ", age=" +age;
    }
}
