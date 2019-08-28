package com.me.testjpa.jpa.java8.optional;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 男人
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Man {
    //女神
    private Godness godness;

    @Override
    public String toString() {
        return "Man{" +
                "godness=" + godness +
                '}';
    }
}
