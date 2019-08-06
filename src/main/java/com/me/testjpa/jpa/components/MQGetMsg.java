package com.me.testjpa.jpa.components;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MQGetMsg {

    @RabbitListener(queues = "myQueue")
    public void getMsg(String msgContent){
        System.out.println("消费端监听到的消息==" + msgContent);
    }
}
