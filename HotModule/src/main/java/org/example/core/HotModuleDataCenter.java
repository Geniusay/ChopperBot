package org.example.core;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.api.HotModuleApi;
import org.example.bean.HotLive;
import org.example.bean.HotModule;
import org.example.bean.hotmodule.HotModuleList;
import org.example.constpool.ConstPool;
import org.example.log.HotModuleLogger;
import org.example.thread.oddjob.OddJobBoy;
import org.example.util.TimeUtil;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Genius
 * @date 2023/07/21 18:44
 **/

/**
 * 热门模块的数据中心
 */
public class HotModuleDataCenter {

    /**
     * 设计这个参数的原因是，虽然HotModule可以存放HotLive数组，但是每天模块更新后会将整个hotModuleListPool替换，导致模块下的热门直播清空，
     * 于是便用hotModuleLivePool来存放每个模块下的热门直播，为了保证访问效率和数据的新鲜度，这里采用 Lazy Delete的方式，
     * 不用每次爬虫获取模块下的热门直播，而是查看是否过期，如果过期了才会去爬虫
     */
    private static final long HOT_MODULE_LIVE_TTL = 60; //热门模块直播过期时间

    private static volatile HotModuleDataCenter dataCenter;


    public static HotModuleDataCenter DataCenter(){
        if(dataCenter==null){
            synchronized (HotModuleDataCenter.class){
                if (dataCenter==null){
                    dataCenter = new HotModuleDataCenter();
                }
            }
        }
        return dataCenter;
    }

    private HotModuleDataCenter(){
        hotModuleListPool = new ConcurrentHashMap<>();

        hotModuleLivePool = new ConcurrentHashMap<>();
        for (ConstPool.PLATFORM value : ConstPool.PLATFORM.values()) {
            hotModuleLivePool.put(value.getName(),new ConcurrentHashMap<>());
        }
        hotLiveListPool = new ConcurrentHashMap<>();
    }

    private ConcurrentHashMap<String, HotModuleList> hotModuleListPool;   //各个平台热门模块列表

    public  ConcurrentHashMap<String,ConcurrentHashMap<HotModule,ModuleLives>> hotModuleLivePool;      //各个平台热门模块直播列表

    private ConcurrentHashMap<String, List<HotLive>> hotLiveListPool;     //各个平台热门直播列表

    public void addModuleList(String platform,HotModuleList hotModuleList){
        hotModuleListPool.put(platform,hotModuleList);
    }

    public void addLiveList(String platform,List<HotLive> hotLiveList){
        hotLiveListPool.put(platform,hotLiveList);
    }

    public void addModuleLiveList(String platform,HotModule hotModule,List<HotLive> hotLiveList){
        ModuleLives moduleLives = new ModuleLives(hotLiveList, LocalDateTime.now());
        hotModule.setHotLives(hotLiveList);
        hotModuleLivePool.get(platform).put(hotModule,moduleLives);
    }

    public HotModuleList getModuleList(String platform){
        return hotModuleListPool.get(platform);
    }

    public List<HotLive> getLiveList(String platform){
        return hotLiveListPool.get(platform);
    }

    public List<HotLive> getModuleLiveList(String platform,HotModule hotModule) throws InterruptedException {
        ModuleLives moduleLives = hotModuleLivePool.get(platform).get(hotModule);
        if(moduleLives!=null){
            if (moduleLives.isExpire()) {
                OddJobBoy.Boy().addWork(()->{
                    addModuleLiveList(platform,hotModule,
                            HotModuleApi.getDouyuHotLive(Integer.parseInt(hotModule.getTagId())));
                    HotModuleLogger.logger.info("platform:{} module:{} hot lives refresh~",platform,hotModule.getTagName());
                });
            }
            return moduleLives.hotLives;
        }else{
            //TODO多次访问改方法时会导致链接中断


            List<HotLive> hotLives = HotModuleApi.getDouyuHotLive(Integer.parseInt(hotModule.getTagId()));
            addModuleLiveList(platform,hotModule,hotLives);
            return hotLives;
        }
    }

    @Data
    @AllArgsConstructor
    class ModuleLives{
        private List<HotLive> hotLives;
        private LocalDateTime updateTime;

        public boolean isExpire(){
            long now = TimeUtil.getCurrentSecond();
            return now - TimeUtil.getSecond(updateTime)>HOT_MODULE_LIVE_TTL;
        }
    }
}
