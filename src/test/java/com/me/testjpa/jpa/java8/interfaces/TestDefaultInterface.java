package com.me.testjpa.jpa.java8.interfaces;



public class TestDefaultInterface {

    public static void main(String[] args) {
        SubClass sc = new SubClass();
        String name = sc.getName();
        System.out.println(name);
    }
}
