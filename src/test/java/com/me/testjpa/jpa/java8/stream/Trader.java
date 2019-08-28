package com.me.testjpa.jpa.java8.stream;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 交易员类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Trader {
    private String name;
    private String city;

    @Override
    public String toString() {
        return "Trader{" +
                "name='" + name + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
