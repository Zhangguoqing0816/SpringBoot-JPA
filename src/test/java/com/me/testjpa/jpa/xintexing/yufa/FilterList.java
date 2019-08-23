package com.me.testjpa.jpa.xintexing.yufa;

import com.me.testjpa.jpa.entity.Employee;

public interface FilterList<T> {

    public boolean test(T t);
}
