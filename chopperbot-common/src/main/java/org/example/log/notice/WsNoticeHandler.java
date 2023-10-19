package org.example.log.notice;

import org.example.ws.MessageHandlerFactory;
import org.example.ws.handler.AbstractMessageHandler;
import org.example.ws.handler.MessageProtocol;
import org.springframework.stereotype.Component;

/**
 * @author Genius
 * @date 2023/10/19 17:29
 **/
@Component
public class WsNoticeHandler implements NoticeHandler{

    private AbstractMessageHandler handler;

    public WsNoticeHandler(NoticeMessageHandler handler) {
        this.handler = handler;
    }

    @Override
    public void doHandler(Notice notice) {
        handler.wrapperAndSend(notice);
    }
}
