package com.me.testjpa.jpa.testdemo;

import lombok.Data;

/**
 * @Description:
 * @Author: ZhangGQ
 * @Date: 2019/9/11 13:53
 */
@Data
public class TestObj1 {
    private String id;
    private String name;
    private TestOjb2 obj2;

    public TestObj1(String id) {
        this.id = id;
    }

    public TestObj1(String id, TestOjb2 obj2) {
        this.id = id;
        this.obj2 = obj2;
    }

    public TestObj1() {
    }

    @Override
    public String toString() {
        return "TestObj1{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}

@Data
class TestOjb2 {
    private String id1;
    private String name1;

    public TestOjb2(String id1) {
        this.id1 = id1;
    }

    public TestOjb2() {
    }

    @Override
    public String toString() {
        return "TestOjb2{" +
                "id1='" + id1 + '\'' +
                ", name1='" + name1 + '\'' +
                '}';
    }
}
