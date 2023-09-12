package org.example.ws;

import org.example.bean.barrage.DouyuBarrage;
import org.java_websocket.client.WebSocketClient;

import org.java_websocket.handshake.ServerHandshake;

import javax.net.ssl.SSLSocketFactory;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;

import static org.example.ws.DouyuProtoctl.*;


/**
 * @author Genius
 * @date 2023/09/04 19:53
 **/
public class WebSocketTest {


    public static WebSocketClient openClient(String roomId) throws InterruptedException {
        String serverUri = "wss://danmuproxy.douyu.com:8502/"; // WebSocket服务器的地址
        long startTime = System.currentTimeMillis();
        WebSocketClient client = null;
        try {
            client = new WebSocketClient(new URI(serverUri), new Draft_6456()) {
                @Override
                public void onOpen(ServerHandshake handshakedata) {
                    System.out.println("WebSocket connection opened");
                    // 发送二进制数据
                    this.send(loginReq(roomId,"visitor4162168","1145012856","20220825","218101901","0"));
                    this.send(joinGroup(roomId,"1"));
                    this.send(heartBeat());
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
                        System.out.println(roomId+":"+douyuBarrage);
                    }
                }

                @Override
                public void onClose(int code, String reason, boolean remote) {
                    this.close();
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
        return client;
    }
    public static void main(String[] args) throws InterruptedException {
        WebSocketClient client = openClient("4520630");
        WebSocketClient client2 = openClient("101367");

        if(client==null||client.isClosed()){

        }else{
            while (true){
                Thread.sleep(60*1000);
                System.out.println("heartBeat");
                client.send(heartBeat());
                client2.send(heartBeat());
                if(client.isClosed()){
                    break;
                }
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
    }

    // 发送二进制数据
}

