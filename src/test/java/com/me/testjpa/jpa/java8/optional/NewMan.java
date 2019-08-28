package com.me.testjpa.jpa.java8.optional;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

/**
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewMan {
    private Optional<Godness> godness = Optional.empty();

    @Override
    public String toString() {
        return "NewMan{" +
                "godness=" + godness +
                '}';
    }
}
