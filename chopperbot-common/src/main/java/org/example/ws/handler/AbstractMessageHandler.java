package org.example.ws.handler;

import org.example.ws.WebSocketHandler;

import java.util.Map;

/**
 * @author Genius
 * @date 2023/09/06 19:45
 **/

public abstract class AbstractMessageHandler implements MessageHandler {
    protected final WebSocketHandler webSocketHandler;

    protected String msgType;

    public AbstractMessageHandler(WebSocketHandler webSocketHandler) {
        this.webSocketHandler = webSocketHandler;
    }

    @Override
    public boolean check(String msg) {
        return MessageProtocol.isThisType(msg,msgType);
    }

    @Override
    public void wrapperAndSend(String msg) {
        String resp = MessageProtocol.encodeMsg(msgType, msg);
        this.webSocketHandler.sendMsg(resp);
    }

    @Override
    public void wrapperAndSend(Map<String, String> msg) {
        String resp = MessageProtocol.encodeMsg(msgType, msg);
        this.webSocketHandler.sendMsg(resp);
    }

    @Override
    public void send(String msg) {
        this.webSocketHandler.sendMsg(msg);
    }
}
