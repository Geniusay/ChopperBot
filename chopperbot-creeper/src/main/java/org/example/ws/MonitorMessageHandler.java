package org.example.ws;


import org.example.api.MonitorCenterApi;
import org.example.ws.WebSocketHandler;
import org.example.ws.handler.AbstractMessageHandler;
import org.example.ws.handler.MessageProtocol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author Genius
 * @date 2023/09/06 19:48
 **/
@Component
public class MonitorMessageHandler extends AbstractMessageHandler {

    @Autowired
    public MonitorMessageHandler(WebSocketHandler webSocketHandler) {
        super(webSocketHandler);
        this.msgType = "monitor";
    }

    @Override
    public void handler(String msg) {
        Map<String, String> data = MessageProtocol.decodeMsg(msg);
        if(data.containsKey("data")){
            String taskId = data.get("data");
            //TODO 获取监视器
            new MonitorCenterApi().start(taskId);
        }
    }

}
