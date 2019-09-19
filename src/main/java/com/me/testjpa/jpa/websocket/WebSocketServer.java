package com.me.testjpa.jpa.websocket;

import com.me.testjpa.jpa.util.WebSocketUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.websocket.*;
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
@ServerEndpoint("/websocket")
//@ServerEndpoint("/websocket")
public class WebSocketServer {
    private final static Logger logger = LoggerFactory.getLogger(WebSocketServer.class);
    private static final AtomicInteger OnlineCount = new AtomicInteger(0);
    private static Logger log = LoggerFactory.getLogger(WebSocketServer.class);
    private static CopyOnWriteArraySet<Session> SessionSet = new CopyOnWriteArraySet<Session>();

    public static CopyOnWriteArraySet<Session> getSessionSet() {
        return SessionSet;
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public static void onMessage(String message, Session session) throws IOException {
        log.info("收到来自客户端的消息:{}", message);
        //SendMessage(session, message);
        WebSocketUtils.receiveAllMessage(session, message);
    }

    /**
     * 发送消息，实践表明，每次浏览器刷新，session会发生变化。
     *
     * @param session
     * @param message
     */
    public static void SendMessage(Session session, String message) {
        try {
            log.info("服务端发送消息:{}----sessionId:{}", message, session.getId());
            session.getBasicRemote().sendText(message);
        } catch (IOException e) {
            log.error("发送消息出错:{}", e.getMessage());
            e.printStackTrace();
        }
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
    public void onOpen(Session session) {
        SessionSet.add(session);
        int cnt = OnlineCount.incrementAndGet(); // 在线数加1
        log.info("有连接加入，当前连接数为：{}", cnt);
        SendMessage(session, "连接成功!!!,当前连接数为:" + cnt);
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(Session session) {
        SessionSet.remove(session);
        int cnt = OnlineCount.decrementAndGet();
        //SendMessage(session, "一个连接关闭!!!,当前连接数为:"+cnt);
        log.info("有连接关闭，当前连接数为：{}", cnt);
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


}
