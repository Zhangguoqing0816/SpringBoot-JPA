package com.me.testjpa.jpa.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    //配置Queue(消息队列).那注意由于采用的是Direct模式,需要在配置Queue的时候,指定一个键,使其和交换机绑定.
    @Bean
    public Queue sendQueue(){
        return new Queue("myQueue", true);
    }

    //声明交换机   new DirectExchange("myQueue", true=是否持久, false=是否自动删除);
    @Bean
    public DirectExchange basicExchange(){
        return new DirectExchange("myExchange", true, false);
    }

    //绑定交换机和队列
    @Bean
    public Binding basicBinding(){
        return BindingBuilder.bind(sendQueue()).to(basicExchange()).with("myQueue");
    }

}
