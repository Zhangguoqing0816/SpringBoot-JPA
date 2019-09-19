package com.me.testjpa.jpa.controller;

import com.me.testjpa.jpa.service.SendMessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Msg")
@Api(value = "消息服务", description = "消息服务")
public class SendMsgController {
    @Autowired
    private SendMessageService sendMessageService;

    @PostMapping("/sendMsg")
    @ApiOperation(value = "发送消息", notes = "发送消息")
    public ResponseEntity sendMsg(String message){
        sendMessageService.sendMessage(message);
        return ResponseEntity.ok(null);
    }
/*
    @GetMapping("/getMsg")
    @ApiOperation(value = "接受消息", notes = "接受消息")
    public ResponseEntity getMsg(){
        sendMessageService.sendMessage(message);
        return ResponseEntity.ok(null);
    }
 */

}
