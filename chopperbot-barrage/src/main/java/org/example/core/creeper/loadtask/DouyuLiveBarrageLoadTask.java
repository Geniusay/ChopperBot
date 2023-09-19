package org.example.core.creeper.loadtask;

import org.example.bean.barrage.DouyuBarrage;
import org.example.cache.FileCache;
import org.example.constpool.PluginName;
import org.example.core.BarrageTaskMonitor;
import org.example.core.creeper.file.BarrageSaveFile;
import org.example.core.creeper.loadconfig.DouyuLiveBarrageLoadConfig;
import org.example.core.creeper.protocl.DouyuProtocol;
import org.example.core.loadtask.WebSocketLoadTask;
import org.example.core.taskmonitor.Monitor;
import org.example.core.taskmonitor.MonitorCenter;
import org.example.exception.FileCacheException;
import org.example.plugin.PluginCheckAndDo;
import org.java_websocket.handshake.ServerHandshake;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author Genius
 * @date 2023/09/12 22:14
 **/
@Monitor(clazz = BarrageTaskMonitor.class)
public class DouyuLiveBarrageLoadTask extends WebSocketLoadTask<List<DouyuBarrage>> {

    private List<DouyuBarrage> list = new ArrayList<>();

    private long startTime = System.currentTimeMillis();

    private String roomId;

    private FileCache fileCache;

    public DouyuLiveBarrageLoadTask(DouyuLiveBarrageLoadConfig loadConfig) throws FileCacheException {
        super(loadConfig);
        roomId = loadConfig.getRoomId();
        fileCache = new FileCache(new BarrageSaveFile<>(loadConfig,new ConcurrentLinkedQueue<>()));
    }

    @Override
    public List<DouyuBarrage> handler() {
        return list;
    }

    @Override
    public List<DouyuBarrage> start() {
        String anchorName = ((DouyuLiveBarrageLoadConfig) loadConfig).getAnchorName();
        this.logger.info("正在爬取{}的直播弹幕...",anchorName);
        return super.start();
    }

    @Override
    public void preHandler() {
        final int[] pre = {list.size()};
        while (!client.isClosed()){
            client.send(DouyuProtocol.heartBeat());
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
        client.send(DouyuProtocol.loginReq(roomId));
        client.send(DouyuProtocol.joinGroup(roomId));
        client.send(DouyuProtocol.heartBeat());
    }

    @Override
    public void onMessage(String s) {

    }

    @Override
    public void onMessage(ByteBuffer buffer) {
        DouyuBarrage douyuBarrage = wrapperBarrage(buffer, startTime);
        if(douyuBarrage!=null){
            if(douyuBarrage.getTimeReal()==0){
                long now = System.currentTimeMillis();
                douyuBarrage.setTimeReal(now);
                douyuBarrage.setTimeReal(now-startTime);
            }
            list.add(douyuBarrage);
            try {
                fileCache.append(douyuBarrage,"-1");
            }catch (FileCacheException | InterruptedException e){
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void onClose(int i, String s, boolean b) {
        client.close();
    }

    @Override
    public void onError(Exception e) {
        client.close();
        this.logger.error("Douyu Live Barrage Error:{}",e.getMessage());
    }

    public DouyuBarrage wrapperBarrage(ByteBuffer buffer, long startTime){
        buffer.position(buffer.position()+12);
        byte[] remainingBytes = new byte[buffer.remaining()];
        buffer.get(remainingBytes);
        String content = new String(remainingBytes, StandardCharsets.UTF_8);
        String[] split = content.split("/");
        if(split.length>0){
            String type = split[0];
            if(type.contains("chatmsg")){
                String txt = "";
                String mid = "";
                long timeReal = 0;
                long timeIndex = 0;
                for(int i=1;i<split.length;i++){
                    String var = split[i];
                    if(var.startsWith("txt@=")){
                        txt = getVar(var,"txt@=");
                    }
                    if(var.startsWith("cid@=")){
                        mid = getVar(var,"cid@=");
                    }
                    if(var.startsWith("cst@=")){
                        String str = getVar(var, "cst@=");
                        timeReal = str.equals("")?System.currentTimeMillis():Long.parseLong(str);
                        if(timeReal<startTime){
                            timeReal = System.currentTimeMillis();
                        }
                        long index = timeReal - startTime;
                        timeIndex = index<0?0:index;
                    }
                }
                return new DouyuBarrage(mid,timeReal,timeIndex,txt);
            }
        }
        return null;
    }

    private String getVar(String var,String param){
        try {
            return var.substring(param.length());
        }catch (Exception e){
            return "";
        }
    }
}
