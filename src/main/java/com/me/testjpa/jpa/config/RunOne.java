package com.me.testjpa.jpa.config;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @Author: ZhangGQ
 * @Date: 2019/09/19
 * Description:
 * 业务场景:
 * 应用服务启动时，加载一些数据和执行一些应用的初始化动作。如：删除临时文件，清除缓存信息，读取配置文件信息，数据库连接等。
 * 1、SpringBoot提供了CommandLineRunner和ApplicationRunner接口。当接口有多个实现类时，提供了@order注解实现自定义执行顺序，也可以实现Ordered接口来自定义顺序。
 * 注意：数字越小，优先级越高，也就是@Order(1)注解的类会在@Order(2)注解的类之前执行。
 * 两者的区别在于：
 * ApplicationRunner中run方法的参数为ApplicationArguments，而CommandLineRunner接口中run方法的参数为String数组。想要更详细地获取命令行参数，那就使用ApplicationRunner接口
 * ————————————————
 * 版权声明：本文为CSDN博主「小贼驴」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。
 * 原文链接：https://blog.csdn.net/weixin_38362455/article/details/83023025
 */
@Component
public class RunOne implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        for (int i = 0; i < 100; i++) {
            System.out.println("现在打印的是：" + i);
        }
    }


    /*@Component
    @Order(value = 11)
    public class AgentApplicationRun implements CommandLineRunner {

        @Override
        public void run(String... strings) throws Exception {

        }
    }*/
}
