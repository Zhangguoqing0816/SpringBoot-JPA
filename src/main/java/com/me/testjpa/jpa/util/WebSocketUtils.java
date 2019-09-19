package com.me.testjpa.jpa.util;

import com.me.testjpa.jpa.websocket.WebSocketServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.Session;
import java.io.IOException;

public class WebSocketUtils {

    private static Logger log = LoggerFactory.getLogger(WebSocketUtils.class);

    /**
     * 群发消息
     *
     * @param message
     * @throws IOException
     */
    public static void sendMessageToAll(String message) throws IOException {
        for (Session session : WebSocketServer.getSessionSet()) {
            if (session.isOpen()) {
                WebSocketServer.SendMessage(session, message);
            }
        }
    }

    /**
     * 指定Session发送消息
     *
     * @param sessionId
     * @param message
     * @throws IOException
     */
    public static void SendMessageBySessionId(String message, String sessionId) throws IOException {
        Session session = null;
        for (Session s : WebSocketServer.getSessionSet()) {
            if (s.getId().equals(sessionId)) {
                session = s;
                break;
            }
        }
        if (session != null) {
            WebSocketServer.SendMessage(session, message);
        } else {
            log.warn("没有找到你指定ID的会话：{}", sessionId);
        }
    }

    /**
     * 接收所有的在线客户的消息
     *
     * @param session 连接的会话对象
     * @param message 该对象发送的消息数据
     * @throws IOException
     */
    public static void receiveAllMessage(Session session, String message) throws IOException {
        log.info("sessionId为:{}的用户推送了消息:{}", session.getId(), message);
    }


}
