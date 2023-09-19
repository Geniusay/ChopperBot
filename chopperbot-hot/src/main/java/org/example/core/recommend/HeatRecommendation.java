package org.example.core.recommend;

/**
 * @author Genius
 * @date 2023/07/22 23:20
 **/

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import org.example.bean.HotModule;
import org.example.bean.Live;
import org.example.cache.FileCacheManagerInstance;
import org.example.bean.FollowDog;
import org.example.config.HotModuleConfig;
import org.example.bean.HotModuleSetting;
import org.example.constpool.ConstGroup;
import org.example.constpool.PluginName;
import org.example.core.HotModuleDataCenter;
import org.example.core.guard.HotModuleGuard;
import org.example.core.manager.CreeperGroupCenter;
import org.example.core.taskcenter.request.ReptileRequest;
import org.example.init.InitPluginRegister;
import org.example.mapper.FollowDogMapper;
import org.example.plugin.CommonPlugin;
import org.example.core.taskcenter.TaskCenter;
import org.example.plugin.SpringBootPlugin;
import org.example.service.FollowDogService;
import org.example.service.HotModuleSettingService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;

/**
 * 根据热门的直播以及看门狗设置来推荐热门直播间到系统
 */
@Data
@Component
public class HeatRecommendation extends SpringBootPlugin {

    private static final long serialVersionUID = 4749216808636623354L;
    private Map<String, List<FollowDog>> platformFollowDogMap ;   //每个平台的跟风列表

    @Resource
    FollowDogService service;

    @Resource
    HotModuleSettingService hotModuleSettingService;


    @Override
    public boolean init() {
        try {
            platformFollowDogMap = new ConcurrentHashMap<>();

            List<HotModuleSetting> modules = hotModuleSettingService.getAllSetting();
            Map<String,HotModuleSetting> map = new HashMap<>();
            modules.forEach(
                    module->{
                        map.put(module.getPlatform(),module);
                        platformFollowDogMap.put(module.getPlatform(),new ArrayList<>());
                    }
            );

            List<FollowDog> allDog = service.getAllDog();
            for (FollowDog followDog : allDog) {
                String platform = followDog.getPlatform();
                if (platformFollowDogMap.containsKey(platform)) {
                    if(map.get(platform).isFollowDogEnable()){
                        platformFollowDogMap.get(platform).add(followDog);
                    }
                }
            }
        }catch (Exception e){
            return false;
        }
        return super.init();
    }

    public void sendHotEvent(String platform){
        handleEvent(platform);
    }

    public void handleEvent(String platform){
        try {
            if(platform!=null){
                List<FollowDog> followDogList;
                logger.info("[{}] {} Hotspot event detected.",platform, PluginName.HOT_RECOMMENDATION_PLUGIN);
                if(platformFollowDogMap.containsKey(platform)
                        &&(followDogList=platformFollowDogMap.get(platform)).size()>0){
                    //发送给爬虫队列
                    List<? extends Live> lives = HotModuleDataCenter.DataCenter().getLiveList(platform);
                    for (FollowDog followDog : followDogList) {
                        String moduleName = followDog.getModuleName();
                        List<? extends Live> tempLives = lives;
                        if(!FollowDog.ALL_LIVES.equals(moduleName)){
                            HotModule module = HotModuleDataCenter.DataCenter().getModule(platform, moduleName);
                            if(module==null){
                                logger.error("[{}] {} not exist the {} module!",PluginName.HOT_RECOMMENDATION_PLUGIN,platform,moduleName);
                                continue;
                            }
                            tempLives = HotModuleDataCenter.DataCenter().getModuleLiveList(platform, module);
                        }
                        if(tempLives==null){
                            logger.error("[{}] cannot found {} {} hot lives",PluginName.HOT_RECOMMENDATION_PLUGIN,platform,moduleName);
                            break;
                        }
                        for (Live live : needRecommend(tempLives, getBanList(followDog.getBanLiver()), followDog.getTop())) {
                            String tempPlatform = live.getPlatform();
                            this.info(String.format("推荐请求:平台 %s,分区 %s,直播间 %s,主播 %s",tempPlatform,live.getModuleName(),live.getLiveId(),live.getLiver()));
                            String checkGroup = CreeperGroupCenter.getGroupName(platform, ConstGroup.LIVER_CHECKER);
                            String liveGroup = CreeperGroupCenter.getGroupName(platform,ConstGroup.LIVE_ONLINE);
                            TaskCenter taskCenter = InitPluginRegister.getPlugin(PluginName.TASK_CENTER_PLUGIN, TaskCenter.class);
                            if (CreeperGroupCenter.getFirstConfig(checkGroup)==null) {
                                taskCenter.request(new ReptileRequest((t)->{
                                    System.out.println(String.format("%s 直播爬取完毕", live.getLiver()));
                                },liveGroup,live));
                            }else{
                                taskCenter.request(new ReptileRequest((obj -> {
                                    obj = obj==null?live:obj;
                                    taskCenter.request(new ReptileRequest((t)->{
                                        System.out.println(String.format("%s 直播爬取完毕", live.getLiver()));
                                    },liveGroup,obj));
                                }),checkGroup,live));
                            }
                        }
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void shutdown() {
        super.shutdown();
    }

    /**
     * 查看热门直播，对比banliver名单，选取前top个名额进行推送推荐名单
     * @param lives
     * @param banLivers
     * @param top
     * @return
     */
    private List<Live> needRecommend(List<? extends Live> lives,List<String> banLivers,int top){
        List<Live> recommendLive = new ArrayList<>();
        List<String> livers = new ArrayList<>();
        int num = 0;

        for (Live live : lives) {
            String liver = live.getLiver();
            if (!isBan(liver,banLivers)) {
                recommendLive.add(live);
                livers.add(liver);
                num++;
            }
            if(num>=top)break;
        }
        return recommendLive;
    }

    private boolean isBan(String liver,List<String> banLivers){
        for (String banLiver : banLivers) {
            if (Pattern.compile(banLiver).matcher(liver).find()) {
                return true;
            }
        }
        return false;
    }

    private List<String> getBanList(String banLiver){
        if(banLiver==null){
            return  new ArrayList<>();
        }else{
            String s = banLiver.substring(1, banLiver.length() - 1);
            String[] ss = s.split(",");
            return List.of(ss);
        }
    }
}
