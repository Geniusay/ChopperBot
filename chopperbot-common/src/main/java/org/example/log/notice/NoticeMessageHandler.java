package org.example.log.notice;

import com.alibaba.fastjson.JSON;
import org.aspectj.bridge.MessageHandler;
import org.example.constpool.ModuleName;
import org.example.constpool.PluginName;
import org.example.ws.WebSocketHandler;
import org.example.ws.handler.AbstractMessageHandler;
import org.example.ws.handler.MessageProtocol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.Session;
import java.util.Map;

/**
 * @author Genius
 * @date 2023/10/19 17:56
 **/
@Component
public class NoticeMessageHandler extends AbstractMessageHandler{

    private final String user = "notice";

    @Autowired
    public NoticeMessageHandler(WebSocketHandler webSocketHandler) {
        super(webSocketHandler);
        this.msgType = "notice";
    }

    @Override
    public void handler(String msg, Session session) {
        Map<String, String> data = MessageProtocol.decodeMsg(msg);
        webSocketHandler.register(user,session);
        this.wrapperAndSend(new Notice().info().from(ModuleName.CHOPPER_BOT).content("通信框架连接成功"));
    }

    @Override
    public void wrapperAndSend(Object msg) {
        String jsonMsg = JSON.toJSONString(msg);
        String resp = MessageProtocol.encodeMsg(msgType,jsonMsg);
        this.webSocketHandler.sendMsg(resp,user);
    }

}
