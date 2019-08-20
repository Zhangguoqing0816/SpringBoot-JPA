package com.me.testjpa.jpa.xintexing;

import com.me.testjpa.jpa.entity.Employee;

public interface FilterList<T> {

    public boolean test(T t);
}
