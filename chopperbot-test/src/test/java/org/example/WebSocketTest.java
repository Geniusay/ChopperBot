package org.example;

import cn.hutool.http.ssl.DefaultSSLFactory;
import org.example.bean.barrage.DouyuBarrage;
import org.java_websocket.client.WebSocketClient;

import org.java_websocket.handshake.ServerHandshake;
import org.junit.Test;
import org.example.ws.Draft_6455;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.ScheduledThreadPoolExecutor;

import static org.example.ws.DouyuProtoctl.*;


/**
 * @author Genius
 * @date 2023/09/04 19:53
 **/
public class WebSocketTest {
    private static WebSocketClient client; // 类级别的WebSocketClient变量

    private static String roomId = "25214";
    public static void main(String[] args) throws InterruptedException {
        String serverUri = "wss://danmuproxy.douyu.com:8505/"; // WebSocket服务器的地址
        long startTime = System.currentTimeMillis();
        try {
            client = new WebSocketClient(new URI(serverUri), new Draft_6455()) {
                @Override
                public void onOpen(ServerHandshake handshakedata) {
                    System.out.println("WebSocket connection opened");
                    // 发送二进制数据
                    client.send(loginReq(roomId,"visitor4090855","1111387111","20220825","218101901","0"));
                    client.send(joinGroup(roomId,"1"));
                    client.send(heartBeat());
                }

                @Override
                public void onMessage(String s) {
                    System.out.println("Received binary data: " + s);
                }

                @Override
                public void onMessage(ByteBuffer buffer) {
                    // 处理二进制消息
                    DouyuBarrage douyuBarrage = wrapperBarrage(buffer, startTime);
                    if(douyuBarrage!=null){
                        System.out.println(douyuBarrage);
                    }
                }

                @Override
                public void onClose(int code, String reason, boolean remote) {
                    client.close();
                    System.out.println(code);
                    System.out.println(reason);
                    System.out.println("WebSocket connection closed");
                }

                @Override
                public void onError(Exception ex) {
                    ex.printStackTrace();
                }
            };
            SSLSocketFactory sslSocketFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            client.setSocketFactory(sslSocketFactory);
            client.connect(); // 连接到WebSocket服务器
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        while (true){
            Thread.sleep(60*1000);
            System.out.println("heartBeat");
            client.send(heartBeat());
            if(client.isClosed()){
                break;
            }
        }
    }

    // 将十六进制字符串转换为字节数组
    private static byte[] hexStringToByteArray(String hex) {
        int len = hex.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(hex.charAt(i), 16) << 4)
                    + Character.digit(hex.charAt(i + 1), 16));
        }
        return data;
    }

    // 发送二进制数据
    private static void sendBinaryData(byte[] data) {
        ByteBuffer buffer = ByteBuffer.wrap(data);
        getClient().send(buffer);
    }

    private static WebSocketClient getClient() {
        return client;
    }
    // 发送二进制数据
}

