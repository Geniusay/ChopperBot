package org.example.ws;

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


    @OnOpen
    public void onOpen(Session session) {
        // WebSocket 连接建立时执行的逻辑
        sessionMap.put("ChopperBot",session);
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        // 处理收到的消息
        MessageHandlerFactory.doHandler(message);
    }

    @OnClose
    public void onClose(Session session, CloseReason closeReason) {
        // WebSocket 连接关闭时执行的逻辑
        sessionMap.remove("ChopperBot");
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
}
