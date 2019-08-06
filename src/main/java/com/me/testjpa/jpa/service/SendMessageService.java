package com.me.testjpa.jpa.service;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SendMessageService {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private DirectExchange directExchange;

    /**
     * 发送消息
     */
    public void sendMessage(String message){
        System.out.println("生产端发送的消息是==" + message);
        rabbitTemplate.convertAndSend(directExchange.getName(), "myQueue", message);
    }

}
