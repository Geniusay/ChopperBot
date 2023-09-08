package org.example.ws;

import org.example.log.ChopperLogFactory;
import org.example.log.LoggerType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Genius
 * @date 2023/09/06 19:24
 **/
@Component
@ServerEndpoint("/chopperBot")
public class WebSocketHandler {

    private static ConcurrentHashMap<String,Session> sessionMap = new ConcurrentHashMap<>();

     private Logger log = ChopperLogFactory.getLogger(LoggerType.System);
    @OnOpen
    public void onOpen(Session session) {
        // WebSocket 连接建立时执行的逻辑
        log.info("{} connect websocket",session);
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        // 处理收到的消息
        log.info("{} send a message:{}",session,message);
        MessageHandlerFactory.doHandler(message,session);
    }

    @OnClose
    public void onClose(Session session, CloseReason closeReason) {
        // WebSocket 连接关闭时执行的逻辑
        ChopperLogFactory.getLogger(LoggerType.System).info("{} close connect~",session);
    }

    public void sendMsg(String message) {
        if(sessionMap.containsKey("ChopperBot")){
            Session chopperBot = sessionMap.get("ChopperBot");
            try {
                chopperBot.getBasicRemote().sendText(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendMsg(String message,String user) {
        if(sessionMap.containsKey(user)){
            Session session = sessionMap.get(user);
            try {
                session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                sessionMap.remove(user);
            }
        }
    }

    public void register(String user,Session session){
        sessionMap.put(user,session);
        log.info("{} {} register websocket",user,session);
    }

    public boolean isRegister(String user){

        return sessionMap.containsKey(user);
    }

    public boolean close(String user){
        if(sessionMap.containsKey(user)){
            Session session = sessionMap.get(user);
            try {
                session.close();
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }
}
