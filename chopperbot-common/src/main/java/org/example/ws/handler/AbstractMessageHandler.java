package org.example.ws.handler;

import com.alibaba.fastjson.JSON;
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
    public void wrapperAndSend(Object msg) {
        String jsonMsg = JSON.toJSONString(msg);
        String resp = MessageProtocol.encodeMsg(msgType,jsonMsg);
        this.webSocketHandler.sendMsg(resp);
    }

    @Override
    public void wrapperAndSend(Map<String, String> msg) {
        String resp = MessageProtocol.encodeMsg(msgType, msg);
        this.webSocketHandler.sendMsg(resp);
    }

    @Override
    public void wrapperAndSend(String msg, String user) {
        String resp = MessageProtocol.encodeMsg(msgType, msg);
        this.webSocketHandler.sendMsg(resp,user);
    }

    @Override
    public void wrapperAndSend(Object msg, String user) {
        String jsonMsg = JSON.toJSONString(msg);
        String resp = MessageProtocol.encodeMsg(msgType,jsonMsg);
        this.webSocketHandler.sendMsg(resp,user);
    }

    @Override
    public void wrapperAndSend(Map<String, String> map, String user) {
        String resp = MessageProtocol.encodeMsg(msgType, map);
        this.webSocketHandler.sendMsg(resp,user);
    }

    @Override
    public void send(String msg) {
        this.webSocketHandler.sendMsg(msg);
    }

    @Override
    public boolean close(String user) {
        return this.webSocketHandler.close(user);
    }
}
