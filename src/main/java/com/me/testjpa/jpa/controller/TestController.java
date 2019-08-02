package com.me.testjpa.jpa.controller;

import ch.qos.logback.core.net.SyslogOutputStream;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TestController {

    public static void main(String[] args) {
        System.out.println(new Date());
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(sf.format(new Date()));
    }
}
