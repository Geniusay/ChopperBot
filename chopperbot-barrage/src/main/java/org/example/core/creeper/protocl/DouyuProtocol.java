package org.example.core.creeper.protocl;

import javax.xml.bind.DatatypeConverter;
import java.nio.ByteBuffer;

/**
 * @author Genius
 * @date 2023/09/12 22:18
 **/
public class DouyuProtocol {

    public static byte[] loginReq(String roomId){
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        String head = "%s000000%s000000b1020000";

        String content = String.format("type@=loginreq/roomid@=%s/dfl@=/" +
                        "username@=visitor4162168/uid@=1145012856/ver@=20220825/aver@=218101901/ct@=0/\0", roomId);
        String lengthStr = Integer.toHexString(content.length()+8);
        buffer.put(DatatypeConverter.parseHexBinary(String.format(head,lengthStr,lengthStr)));
        buffer.put(content.getBytes());
        buffer.flip();
        byte[] byteArray = new byte[buffer.remaining()];
        // 将ByteBuffer中的内容复制到byte数组中
        buffer.get(byteArray);
        return byteArray;
    }

    public static byte[] joinGroup(String roomId){
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        String head = "%s000000%s000000b1020000";
        String content = String.format("type@=joingroup/rid@=%s/gid@=1/\0",roomId);
        String lengthStr = Integer.toHexString(content.length()+8);
        buffer.put(DatatypeConverter.parseHexBinary(String.format(head,lengthStr,lengthStr)));
        buffer.put(content.getBytes());
        buffer.flip();
        byte[] byteArray = new byte[buffer.remaining()];
        // 将ByteBuffer中的内容复制到byte数组中
        buffer.get(byteArray);
        return byteArray;
    }

    public static byte[] heartBeat(){
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        String head = "1400000014000000b1020000";
        buffer.put(DatatypeConverter.parseHexBinary(head));
        String content = "type@=mrkl/\0";
        buffer.put(content.getBytes());
        buffer.flip();
        byte[] byteArray = new byte[buffer.remaining()];
        buffer.get(byteArray);
        return byteArray;
    }


}
