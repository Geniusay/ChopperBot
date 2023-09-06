package org.example.ws.handler;


import java.util.Map;

/**
 * @author Genius
 * @date 2023/09/06 19:28
 **/
public interface MessageHandler {

    boolean check(String msg);
    void handler(String msg);
    void wrapperAndSend(String msg);

    void wrapperAndSend(Map<String,String> msg);

    void send(String msg);
}
