package org.example.ws;

import org.example.ws.handler.AbstractMessageHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.websocket.Session;
import java.util.List;

/**
 * @author Genius
 * @date 2023/09/06 19:34
 **/
@Component
public class MessageHandlerFactory implements CommandLineRunner {

    private static List<AbstractMessageHandler> link;

    @Autowired
    public MessageHandlerFactory(List<AbstractMessageHandler> link){
        MessageHandlerFactory.link = link;
    }

    public static void doHandler(String msg, Session session){
        for (AbstractMessageHandler abstractMessageHandler : link) {
            if (abstractMessageHandler.check(msg)) {
                abstractMessageHandler.handler(msg,session);
                return;
            }
        }
    }

    public static AbstractMessageHandler getHandler(String type){
        for (AbstractMessageHandler abstractMessageHandler : link) {
            if (abstractMessageHandler.check(type)) {
                return abstractMessageHandler;
            }
        }
        return null;
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
