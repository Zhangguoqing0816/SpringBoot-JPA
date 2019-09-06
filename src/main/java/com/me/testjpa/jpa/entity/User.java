package com.me.testjpa.jpa.entity;

import lombok.Data;

/**
 * @Description:
 * @Author: ZhangGQ
 * @Date: 2019/9/6 10:31
 */
@Data
public class User {


    private String name;
    private String age;
    private String sex;

    public User(String name, String age, String sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }
}
