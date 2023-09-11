package org.example.ws;

//import org.example.bean.barrage.DouyuBarrage;
//import org.example.util.TimeUtil;
//import javax.xml.bind.DatatypeConverter;
//import java.nio.ByteBuffer;
//import java.nio.charset.StandardCharsets;
//import java.text.SimpleDateFormat;
//import java.util.Date;

/**
 * @author Genius
 * @date 2023/09/04 20:26
 **/
public class DouyuProtoctl {

//    public static byte[] loginReq(String roomId,String username,String uid,String ver,String aver,String ct){
//        ByteBuffer buffer = ByteBuffer.allocate(1024);
//        String head = "8d0000008d000000b1020000";
//        buffer.put(DatatypeConverter.parseHexBinary(head));
//        String content = String.format("type@=loginreq/roomid@=%s/dfl@=sn/username@=%s@AA=105@ASss@AA=1/uid@=%s/ver@=%s/aver@=%s/ct@=%s/\0",
//                roomId,username,uid,ver,aver,ct);
//        buffer.put(content.getBytes());
//        buffer.flip();
//        byte[] byteArray = new byte[buffer.remaining()];
//        // 将ByteBuffer中的内容复制到byte数组中
//        buffer.get(byteArray);
//        return byteArray;
//    }
//
//    public static byte[] joinGroup(String roomId,String gid){
//        ByteBuffer buffer = ByteBuffer.allocate(1024);
//        String head = "2c0000002c000000b1020000";
//        buffer.put(DatatypeConverter.parseHexBinary(head));
//        String content = String.format("type@=joingroup/rid@=%s/gid@=%s/\0",roomId,gid);
//        buffer.put(content.getBytes());
//        buffer.flip();
//        byte[] byteArray = new byte[buffer.remaining()];
//        // 将ByteBuffer中的内容复制到byte数组中
//        buffer.get(byteArray);
//        return byteArray;
//    }
//
//    public static byte[] heartBeat(){
//        ByteBuffer buffer = ByteBuffer.allocate(1024);
//        String head = "1400000014000000b1020000";
//        buffer.put(DatatypeConverter.parseHexBinary(head));
//        String content = "type@=mrkl/\0";
//        buffer.put(content.getBytes());
//        buffer.flip();
//        byte[] byteArray = new byte[buffer.remaining()];
//        buffer.get(byteArray);
//        return byteArray;
//    }
//
//    public static ByteBuffer heartBeat2(){
//        ByteBuffer buffer = ByteBuffer.allocate(1024);
//        String head = "1400000014000000b1020000";
//        buffer.put(DatatypeConverter.parseHexBinary(head));
//        String content = "type@=mrkl/\0";
//        buffer.put(content.getBytes());
//        buffer.flip();
//        return buffer;
//    }
//
//    public static DouyuBarrage wrapperBarrage(ByteBuffer buffer,long startTime){
//        buffer.position(buffer.position()+12);
//        byte[] remainingBytes = new byte[buffer.remaining()];
//        buffer.get(remainingBytes);
//        String content = new String(remainingBytes,StandardCharsets.UTF_8);
//        String[] split = content.split("/");
//        if(split.length>0){
//            String type = split[0];
//            if(type.contains("chatmsg")){
//                String txt = "";
//                String mid = "";
//                long timeReal = 0;
//                long timeIndex = 0;
//                for(int i=1;i<split.length;i++){
//                    String var = split[i];
//                    if(var.startsWith("txt@=")){
//                        txt = getVar(var,"txt@=");
//                    }
//                    if(var.startsWith("cid@=")){
//                        mid = getVar(var,"cid@=");
//                    }
//                    if(var.startsWith("cst@=")){
//                        String str = getVar(var, "cst@=");
//                        timeReal = str.equals("")?System.currentTimeMillis():Long.parseLong(str);
//                        timeIndex = timeReal - startTime;
//                    }
//                }
//                return new DouyuBarrage(mid,timeReal,timeIndex,txt);
//            }
//        }
//        return null;
//    }
//
//    private static String getVar(String var,String param){
//        try {
//            return var.substring(param.length());
//        }catch (Exception e){
//            return "";
//        }
//    }
//
//    public static void main(String[] args) {
//        long timestamp = 1694246297000L; // 你的时间戳
//        Date date = new Date(timestamp);
//
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String formattedDate = sdf.format(date);
//
//        System.out.println(formattedDate);
//
//        System.out.println(TimeUtil.getTimeNaos("2023-09-05 18:10:08"));
//    }

}
