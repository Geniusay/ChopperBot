package org.example.ws.handler;


import javax.websocket.Session;
import java.util.Map;

/**
 * @author Genius
 * @date 2023/09/06 19:28
 **/
public interface MessageHandler {

    boolean check(String msg);
    void handler(String msg, Session session);
    void wrapperAndSend(String msg);
    void wrapperAndSend(Object msg);

    void wrapperAndSend(Map<String,String> msg);
    void wrapperAndSend(String msg,String user);
    void wrapperAndSend(Object msg,String user);
    void wrapperAndSend(Map<String,String> map,String user);

    void send(String msg);

    boolean close(String user);
}
