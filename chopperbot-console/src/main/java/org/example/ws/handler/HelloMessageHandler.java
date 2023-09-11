package org.example.ws.handler;

import org.example.ws.WebSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.Session;

/**
 * @author Genius
 * @date 2023/09/06 20:02
 **/
@Component
public class HelloMessageHandler extends AbstractMessageHandler {

    @Autowired
    public HelloMessageHandler(WebSocketHandler webSocketHandler) {
        super(webSocketHandler);
        this.msgType = "hello";
    }

    @Override
    public void handler(String msg, Session session) {
        send(msg);
    }

}
