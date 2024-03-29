package org.example.core.focus;

import org.example.bean.FocusLiver;
import org.example.cache.FileCache;
import org.example.cache.FileCacheManager;
import org.example.config.HotModuleConfig;
import org.example.constpool.ConstGroup;
import org.example.constpool.PluginName;
import org.example.core.loadtask.LoadTask;
import org.example.core.manager.CreeperGroupCenter;
import org.example.core.manager.CreeperManager;
import org.example.core.taskcenter.TaskCenter;
import org.example.core.taskcenter.request.ReptileRequest;
import org.example.init.InitPluginRegister;
import org.example.mapper.FocusLiverMapper;
import org.example.plugin.SpringBootPlugin;
import org.example.service.FocusLiverService;
import org.example.sql.annotation.SQLInit;
import org.example.thread.NamedThreadFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.*;
import java.util.concurrent.*;

/**
 * @author Genius
 * @date 2023/09/09 23:20
 **/
@Component
public class LiverFollower extends SpringBootPlugin{

    @Resource
    private FocusLiverService service;

    private boolean focusLive = true;

    private boolean focusBarrage = true;
    private boolean focusRecord = true;

    private long checkTime = 60000;

    List<FocusLiver> focusLivers;

    private ScheduledExecutorService focusPool;

    private Map<String, Future> focusFuture;

    @Override
    public boolean init() {
        try {
            FileCacheManager plugin = InitPluginRegister.getPlugin(PluginName.FILE_CACHE_PLUGIN, FileCacheManager.class);
            assert plugin != null;
            FileCache fileCache = plugin.getFileCache(HotModuleConfig.getFullFilePath());
            focusLive = (Integer)fileCache.get("LiverFollower", "focusLive")==1;
            //focusBarrage = (Integer)fileCache.get("LiverFollower", "focusBarrage")==1;
            checkTime = Long.parseLong(fileCache.get("LiverFollower", "checkTime").toString());
            focusRecord = (Integer)fileCache.get("LiverFollower", "focusRecord")==1;
            focusLivers = service.getFocusLivers();
            info(String.format("Find %s following anchors", focusLivers.size()));
            focusFuture = new HashMap<>();
            focusPool = Executors.newScheduledThreadPool(focusLivers.size()+5, new NamedThreadFactory(pluginName));
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        return true;
    }

    @Override
    public void afterInit(){
        doFocus();
    }
    private void doFocus(){
        focusLivers.forEach(
                this::appendLiverTask
        );
    }

    private void appendLiverTask(FocusLiver liver){
        String function = ConstGroup.LIVER_CHECKER;
        String platform = liver.getPlatform();
        String groupName = CreeperGroupCenter.getGroupName(platform, function);
        CreeperManager plugin = InitPluginRegister.getPlugin(PluginName.CREEPER_MANAGER_PLUGIN, CreeperManager.class);
        assert plugin != null;
        LoadTask loadTask = plugin.getLoadTask(groupName, liver);
        if(loadTask!=null){
            ScheduledFuture<?> schedule = focusPool.scheduleWithFixedDelay(new FollowerEyes(loadTask, platform, liver.getLiver()),0,
                    checkTime, TimeUnit.MILLISECONDS);
            focusFuture.put(liver.getLiver(),schedule);
            this.info(String.format("eyes on the liver:%s", liver.getLiver()));
        }else{
            error(String.format("cant found platform:%s %s creeper",platform,groupName));
        }
    }
    public void addFocusLiver(FocusLiver liver){
        this.appendLiverTask(liver);
    }

    public void deleteFocusLiver(String liver){
        Future task = focusFuture.remove(liver);
        if(task!=null){
            task.cancel(true);
        }
    }

    class FollowerEyes implements Runnable,Serializable{

        private LoadTask checker;

        private String platform;
        private String liver;

        private TaskCenter taskCenter = InitPluginRegister.getPlugin(PluginName.TASK_CENTER_PLUGIN, TaskCenter.class);

        private String liveTaskId = null;
        public FollowerEyes(LoadTask checker, String platform, String liver) {
            this.checker = checker;
            this.platform = platform;
            this.liver = liver;
        }

        @Override
        public void run() {
            if(!alreadyFocus()){
                Object live = checker.start();
                if(live!=null){
                    LiverFollower plugin = InitPluginRegister.getPlugin(PluginName.HOT_LIVER_FOLLOWER, LiverFollower.class);
                    plugin.info(String.format("主播：%s 开播啦！！！！", liver),true);
                    doLiveCreeper(live);
                }
            }

        }

        public void doLiveCreeper(Object obj){
            if(focusLive){
                String groupName = CreeperGroupCenter.getGroupName(platform, ConstGroup.LIVE_ONLINE);
                liveTaskId = taskCenter.request( new ReptileRequest((t)->{
                    liveTaskId = null;
                },groupName,obj));
            }
        }

        public boolean alreadyFocus(){
            if(focusLive){
                if(liveTaskId==null)return false;
                if(!taskCenter.isRunning(liveTaskId)){
                    return false;
                }
            }
            return true;
        }
    }

    @Override
    public void shutdown() {
        focusFuture.forEach(
                (k,f)->{
                    f.cancel(true);
                }
        );
        focusPool.shutdown();
        super.shutdown();
    }

    @Override
    @SQLInit(table = "focus_liver",tableSQL = "CREATE TABLE \"focus_liver\" (\n" +
            "\t\"id\"\tINTEGER UNIQUE,\n" +
            "\t\"liver\"\tTEXT NOT NULL UNIQUE,\n" +
            "\t\"room_id\"\tTEXT NOT NULL UNIQUE,\n" +
            "\t\"platform\"\tTEXT NOT NULL,\n" +
            "\t\"tag\"\tTEXT,\n" +
            "\t\"avatar\"\tTEXT,\n" +
            "\t\"is_auto\"\tINTEGER NOT NULL DEFAULT 1,\n" +
            "\tPRIMARY KEY(\"id\" AUTOINCREMENT)\n" +
            ")",mapper = FocusLiverMapper.class)
    public List<FocusLiver> sqlInit() {
        return null;
    }
}
