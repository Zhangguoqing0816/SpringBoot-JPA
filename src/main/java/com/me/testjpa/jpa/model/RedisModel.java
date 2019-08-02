package com.me.testjpa.jpa.model;

import com.me.testjpa.jpa.entity.Employee;
import lombok.Data;

import java.io.Serializable;

@Data
public class RedisModel implements Serializable {

    private String id;

    private String name;

    private String content;

    private Employee emp;
}
