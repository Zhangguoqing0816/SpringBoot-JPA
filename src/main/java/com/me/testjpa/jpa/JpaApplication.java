package com.me.testjpa.jpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class JpaApplication {

    //One
    //zhangBranchOne
    // This is master
    public static void main(String[] args) {
        SpringApplication.run(JpaApplication.class, args);
    }

}
