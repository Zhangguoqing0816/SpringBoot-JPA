package com.me.testjpa.jpa.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: ZhangGQ
 * @Date: 2019/09/17
 * Description:
 * 因为WebSocket是类似客户端服务端的形式(采用ws协议)，那么这里的WebSocketServer其实就相当于一个ws协议的Controller
 * 直接@ServerEndpoint("/websocket")@Component启用即可，然后在里面实现@OnOpen,@onClose,@onMessage等方法
 */
@Component
@ServerEndpoint("/websocket/{sid}")
public class WebSocketServer2 {
    private final static Logger logger = LoggerFactory.getLogger(WebSocketServer2.class);
    private static final AtomicInteger OnlineCount = new AtomicInteger(0);
    private static Logger log = LoggerFactory.getLogger(WebSocketServer2.class);
    private static CopyOnWriteArraySet<WebSocketServer2> webSocketSet = new CopyOnWriteArraySet<>();
    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;
    //接收sid
    private String sid = "";

    public static CopyOnWriteArraySet<WebSocketServer2> getSessionSet() {
        return webSocketSet;
    }

    @PostConstruct
    public void init() {
        log.info("正在加载webSocket服务!!!");
    }

    @PreDestroy
    public void destroy() {
        log.info("webSocket服务已经被释放!!!");
    }

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("sid") String sid) {
        this.session = session;
        this.sid = sid;
        webSocketSet.add(this);
        int cnt = OnlineCount.incrementAndGet(); // 在线数加1
        log.info("有新窗口开始监听:" + sid + ",当前在线人数为" + cnt);
        try {
//            sendMessage("连接成功!!!,当前连接数为:" + cnt);
            sendInfo("连接成功!!!,当前连接数为:" + cnt, sid);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(Session session) {
        webSocketSet.remove(this);
        int cnt = OnlineCount.decrementAndGet();
        //SendMessage(session, "一个连接关闭!!!,当前连接数为:"+cnt);
        log.info("有连接关闭，当前连接数为：{}", cnt);
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, @PathParam("sid") String sid) throws IOException {
        log.info("收到来自客户端的消息:{}", message);
//        sendMessage(message);
        sendInfo(message, sid);
    }

    /**
     * 出现错误
     *
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("发生错误:{},Session ID:{}", error.getMessage(), session.getId());
        error.printStackTrace();
    }

    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    public void sendInfo(String message, @PathParam("sid") String sid) throws IOException {
        log.info("推送消息到窗口" + sid + "，推送内容:" + message);
        for (WebSocketServer2 item : webSocketSet) {
            try {
                //这里可以设定只推送给这个sid的，为null则全部推送
                if (sid == null) {
                    item.sendMessage(message);
                } else if (item.sid.equals(sid)) {
                    item.sendMessage(message);
                }
            } catch (IOException e) {
                continue;
            }
        }
    }

}
