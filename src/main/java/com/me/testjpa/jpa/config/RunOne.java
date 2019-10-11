package com.me.testjpa.jpa.config;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @Author: ZhangGQ
 * @Date: 2019/09/19
 * Description:
 */
@Component
public class RunOne implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        for (int i = 0; i < 100; i++) {
            System.out.println("现在打印的是：" + i);
        }
    }
}
