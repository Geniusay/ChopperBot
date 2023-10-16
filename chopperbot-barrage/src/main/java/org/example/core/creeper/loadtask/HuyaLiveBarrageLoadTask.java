package org.example.core.creeper.loadtask;

import com.qq.tars.protocol.tars.TarsInputStream;
import org.example.bean.barrage.DouyuBarrage;
import org.example.bean.barrage.HuyaBarrage;
import org.example.cache.FileCache;
import org.example.constpool.PluginName;
import org.example.core.BarrageFileCache;
import org.example.core.BarrageTaskMonitor;
import org.example.core.creeper.file.BarrageSaveFile;
import org.example.core.creeper.loadconfig.DouyuLiveBarrageLoadConfig;
import org.example.core.creeper.loadconfig.HuyaLiveBarrageLoadConfig;
import org.example.core.creeper.protocl.DouyuProtocol;
import org.example.core.creeper.protocl.HuyaProtocol;
import org.example.core.loadtask.WebSocketLoadTask;
import org.example.core.taskmonitor.Monitor;
import org.example.core.taskmonitor.MonitorCenter;
import org.example.exception.FileCacheException;
import org.example.plugin.PluginCheckAndDo;
import org.java_websocket.handshake.ServerHandshake;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author dhx
 * @date 2023/10/16 16:03
 */
@Monitor(clazz = BarrageTaskMonitor.class)
public class HuyaLiveBarrageLoadTask extends WebSocketLoadTask<List<HuyaBarrage>> {
    private List<HuyaBarrage> list = new ArrayList<>();

    private long startTime = System.currentTimeMillis();

    private String roomId;

    private FileCache fileCache;
    public HuyaLiveBarrageLoadTask(HuyaLiveBarrageLoadConfig loadConfig) throws FileCacheException {
        super(loadConfig);
        roomId = loadConfig.getRoomId();
        fileCache = new BarrageFileCache(new BarrageSaveFile<>(loadConfig,new ConcurrentLinkedQueue<>()));
    }

    public static String stringToHex(String input) {
        StringBuilder hexString = new StringBuilder();
        for (char c : input.toCharArray()) {
            hexString.append(String.format("%02X ", (int) c));
        }
        return hexString.toString();
    }

    @Override
    public List<HuyaBarrage> handler() {
        return list;
    }

    @Override
    public List<HuyaBarrage> start() {
        String anchorName = ((HuyaLiveBarrageLoadConfig) loadConfig).getAnchorName();
        this.logger.info("正在爬取{}的直播弹幕...",anchorName);
        return super.start();
    }

    @Override
    public void preHandler(){
        final int[] pre = {list.size()};
        while (!client.isClosed()){
            client.send(HuyaProtocol.getHeartbeat());
            try {
                Thread.sleep(1000*60);
                PluginCheckAndDo.CheckAndDo(plugin -> {
                    BarrageTaskMonitor monitor = ((MonitorCenter) plugin).getInitMonitor(this.loadConfig.getTaskId(), BarrageTaskMonitor.class);
                    if(monitor!=null){
                        monitor.addBarrage(list.size()- pre[0]);
                        pre[0] = list.size();
                    }
                }, PluginName.TASK_MONITOR_PLUGIN);
            }catch (InterruptedException e){
                break;
            }
            fileCache.forceSync();
        }
    }

    @Override
    public void onOpen(ServerHandshake serverHandshake) {
        client.send(HuyaProtocol.getWsInfo(roomId));
        client.send(HuyaProtocol.getHeartbeat());
    }

    @Override
    public void onMessage(String s) {
        System.out.println(s);
    }

    @Override
    public void onMessage(ByteBuffer buffer) {
        HuyaBarrage huyaBarrage = wrapperBarrage(buffer,0);
    }

    @Override
    public void onClose(int i, String s, boolean b) {
        client.close();
    }

    @Override
    public void onError(Exception e) {
        client.close();
        this.logger.error("Huya Live Barrage Error:{}",e.getMessage());
    }

    public HuyaBarrage wrapperBarrage(ByteBuffer buffer, long startTime){
        String name = "";
        String content = "";
        TarsInputStream tarsInputStream = new TarsInputStream(buffer);
        int int32 = 0;
        long int64 = 0L;
        byte[] bytes = null;
        if(tarsInputStream.read(int32,0,false) == 7){
            tarsInputStream = new TarsInputStream(tarsInputStream.read(bytes,1,false));
            if(tarsInputStream.read(int64,1,false)==1400){
                tarsInputStream = new TarsInputStream(tarsInputStream.read(bytes,2,false));
//                name = tarsInputStream.read("",0,false);
                content = tarsInputStream.read("", 3, true);
                try {
                    System.out.println(new String(content.getBytes("GBK")));
                    System.out.println(stringToHex(content));
                    System.out.println(new String(content.replaceAll("\uFFFD", "").getBytes("GBK")));
                    System.out.println(stringToHex(new String(content.getBytes("GBK"))));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                System.out.println(name);

            }
        }
        return null;
    }
}
