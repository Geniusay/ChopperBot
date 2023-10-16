package org.example.core.loadtask;

import org.example.bean.barrage.DouyuBarrage;
import org.example.core.loadconfig.LoadConfig;
import org.example.util.ExceptionUtil;
import org.example.ws.Draft_6455;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import javax.net.ssl.SSLSocketFactory;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.util.concurrent.TimeUnit;

/**
 * @author Genius
 * @date 2023/09/12 21:45
 **/
public abstract class WebSocketLoadTask<T> extends CommonLoadTask<T>{

    protected WebSocketClient client;

    public WebSocketLoadTask(LoadConfig loadConfig){
        super(loadConfig);
    }

    @Override
    public T start() {
        try {
            client = new WebSocketClient(new URI(loadConfig.getUrl()),new Draft_6455()) {
                @Override
                public void onOpen(ServerHandshake serverHandshake) {
                    WebSocketLoadTask.this.onOpen(serverHandshake);
                }

                @Override
                public void onMessage(String s) {
                    WebSocketLoadTask.this.onMessage(s);
                }

                @Override
                public void onMessage(ByteBuffer buffer) {
                    // 处理二进制消息
                    WebSocketLoadTask.this.onMessage(buffer);
                }

                @Override
                public void onClose(int i, String s, boolean b) {
                    WebSocketLoadTask.this.onClose(i,s,b);
                }

                @Override
                public void onError(Exception e) {
                    WebSocketLoadTask.this.onError(e);
                }
            };
            SSLSocketFactory sslSocketFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            client.setSocketFactory(sslSocketFactory);
            client.connectBlocking(60, TimeUnit.SECONDS);
        }catch (URISyntaxException | InterruptedException e){
            this.logger.error("Websocket creeper error:{}", ExceptionUtil.getCause(e));
            return null;
        }
        if(client==null){
            return null;
        }
        preHandler();
        return handler();
    }

    public void preHandler(){
        try {
            client.closeBlocking();
        }catch (InterruptedException e){

        }
    }
    public abstract T handler();

    public abstract void onOpen(ServerHandshake serverHandshake);

    public abstract void onMessage(String s);

    public abstract void onMessage(ByteBuffer buffer);

    public abstract void onClose(int i,String s,boolean b);

    public abstract void onError(Exception e);


    @Override
    public void end() {
        client.close();
    }
}
