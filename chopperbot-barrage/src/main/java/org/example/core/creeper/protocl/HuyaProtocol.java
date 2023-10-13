package org.example.core.creeper.protocl;

import com.qq.tars.protocol.tars.TarsOutputStream;
//import org.example.util.HttpClientUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author dhx
 * @date 2023/10/10 19:49
 */
public class HuyaProtocol {
    private static final String wssUrl = "wss://cdnws.api.huya.com/";
    private static final byte[] heartbeat = new byte[]{0x00, 0x03, 0x1d, 0x00, 0x00, 0x69, 0x00, 0x00, 0x00, 0x69,
            0x10, 0x03, 0x2c, 0x3c, 0x4c, 0x56, 0x08, 0x6f, 0x6e, 0x6c, 0x69, 0x6e, 0x65, 0x75, 0x69, 0x66, 0x0f,
            0x4f, 0x6e, 0x55, 0x73, 0x65, 0x72, 0x48, 0x65, 0x61, 0x72, 0x74, 0x42, 0x65, 0x61, 0x74, 0x7d, 0x00,
            0x00, 0x3c, 0x08, 0x00, 0x01, 0x06, 0x04, 0x74, 0x52, 0x65, 0x71, 0x1d, 0x00, 0x00, 0x2f, 0x0a, 0x0a,
            0x0c, 0x16, 0x00, 0x26, 0x00, 0x36, 0x07, 0x61, 0x64, 0x72, 0x5f, 0x77, 0x61, 0x70, 0x46, 0x00, 0x0b,
            0x12, 0x03, (byte) 0xae, (byte) 0xf0, 0x0f, 0x22, 0x03, (byte) 0xae, (byte) 0xf0, 0x0f, 0x3c, 0x42, 0x6d,
            0x52, 0x02, 0x60, 0x5c, 0x60, 0x01, 0x7c, (byte) 0x82, 0x00, 0x0b, (byte) 0xb0, 0x1f, (byte) 0x9c,
            (byte) 0xac, 0x0b, (byte) 0x8c, (byte) 0x98, 0x0c, (byte) 0xa8, 0x0c};
    private static final int heartbeatInterval = 60;
    public static String getWsInfo(String url) throws IOException {
        ArrayList<byte[]> regDatas = new ArrayList<>();
        url = "https://m.huya.com/" + url.substring(url.lastIndexOf('/') + 1);
        Map<String, String> header = new HashMap<>();
        header.put("user-agent", "Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Mobile Safari/537.36");
        header.put("Content-Type", "application/x-www-form-urlencoded");
//        String roomPage = HttpClientUtil.get(url, header);
         String roomPage ="";
        Pattern pattern = Pattern.compile("lYyid\":([0-9]+)");
        Matcher matcher = pattern.matcher(roomPage);
        String ayyuid = "";
        if (matcher.find()) {
            ayyuid = matcher.group(1);
        }

        pattern = Pattern.compile("lChannelId\":([0-9]+)");
        matcher = pattern.matcher(roomPage);
        String tid = "";
        if (matcher.find()) {
            tid = matcher.group(1);
        }

        pattern = Pattern.compile("lSubChannelId\":([0-9]+)");
        matcher = pattern.matcher(roomPage);
        String sid = "";
        if (matcher.find()) {
            sid = matcher.group(1);
        }

        long ayyuidLong = Long.parseLong(ayyuid);
        long tidLong = Long.parseLong(tid);
        long sidLong = Long.parseLong(sid);

        TarsOutputStream oos = new TarsOutputStream();
        oos.write(ayyuidLong, 0);
        oos.write(true, 1); // Anonymous
        oos.write("", 2); // sGuid
        oos.write("", 3);
        oos.write(tidLong, 4);
        oos.write(sidLong, 5);
        oos.write(0L, 6);
        oos.write(0L, 7);

        TarsOutputStream wscmd = new TarsOutputStream();
        wscmd.write(1, 0);
        wscmd.write(oos.getByteBuffer().array(), 1);

        regDatas.add(wscmd.getByteBuffer().array());
        return wssUrl+regDatas;
    }

}
