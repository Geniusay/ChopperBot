package org.example.core.recommend;

/**
 * @author Genius
 * @date 2023/07/22 23:20
 **/

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson2.util.TypeUtils;
import lombok.Data;
import org.example.bean.HotModule;
import org.example.bean.Live;
import org.example.cache.FileCacheManagerInstance;
import org.example.config.FollowDog;
import org.example.config.HotModuleConfig;
import org.example.config.HotModuleSetting;
import org.example.constpool.PluginName;
import org.example.core.HotModuleDataCenter;
import org.example.core.creeper.loadconfig.*;
import org.example.core.manager.CreeperGroupCenter;
import org.example.core.manager.CreeperManager;
import org.example.core.taskcenter.request.ReptileRequest;
import org.example.core.taskcenter.task.TaskRecord;
import org.example.init.InitPluginRegister;
import org.example.plugin.CommonPlugin;
import org.example.plugin.GuardPlugin;
import org.example.core.taskcenter.TaskCenter;
import org.example.plugin.PluginCheckAndDo;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

/**
 * 根据热门的直播以及看门狗设置来推荐热门直播间到系统
 */
@Data
public class HeatRecommendation extends CommonPlugin {

    private static final long serialVersionUID = 4749216808636623354L;
    private Map<String, List<FollowDog>> platformFollowDogMap ;   //每个平台的跟风列表

    public HeatRecommendation(String module, String pluginName, List<String> needPlugins, boolean isAutoStart) {
        super(module, pluginName, needPlugins, isAutoStart);
    }

    @Override
    public boolean init() {
        try {
            platformFollowDogMap = new ConcurrentHashMap<>();

            List<HotModuleSetting> modules = new ArrayList<>();

            JSONArray jsonModules = (JSONArray) FileCacheManagerInstance
                    .getInstance()
                    .getFileCache(HotModuleConfig.getFullFilePath())
                    .get("Module");


            jsonModules.forEach(jsonModule->{
                modules.add(JSONObject.parseObject(jsonModule.toString(),HotModuleSetting.class));
            });

            for (HotModuleSetting module : modules) {
                if (module.isFollowDogEnable()) {
                    platformFollowDogMap.put(module.getPlatform(),module.getFollowDogs());
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
                        for (Live live : needRecommend(tempLives, followDog.getBanLiver(), followDog.getTop())) {
                            String tempPlatform = live.getPlatform();
                            this.info(String.format("推荐请求:平台 %s,分区 %s,直播间 %s,主播 %s",tempPlatform,live.getModuleName(),live.getLiveId(),live.getLiver()));
                            PluginCheckAndDo.CheckAndDo(
                                    taskCenter -> {
                                        ((TaskCenter)taskCenter).request(new ReptileRequest((t)->{
                                            System.out.printf("%s 直播已结束", t);
                                        }, CreeperGroupCenter.getGroupName(platform,"live"),live));

                                        ((TaskCenter)taskCenter).request(new ReptileRequest((t)->{
                                            System.out.printf("%s 直播弹幕爬取完毕", t);
                                        }, CreeperGroupCenter.getGroupName(platform,"live_barrage"),live));
                                    },
                                    PluginName.TASK_CENTER_PLUGIN
                            );
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



}
