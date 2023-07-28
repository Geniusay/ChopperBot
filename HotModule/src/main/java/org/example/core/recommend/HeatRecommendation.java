package org.example.core.recommend;

/**
 * @author Genius
 * @date 2023/07/22 23:20
 **/

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.example.bean.Live;
import org.example.cache.FileCacheManagerInstance;
import org.example.config.FollowDog;
import org.example.config.HotModuleConfig;
import org.example.config.HotModuleSetting;
import org.example.core.HotModuleDataCenter;
import org.example.log.ChopperLogFactory;
import org.example.log.LoggerType;
import org.example.taskcenter.TaskCenter;
import org.example.taskcenter.request.LiveReptileRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;

/**
 * 根据热门的直播以及看门狗设置来推荐热门直播间到系统
 */
public class HeatRecommendation {

    private Map<String, Live> recommendationLog;        //推送日志

    private Map<String, List<FollowDog>> platformFollowDogMap ;   //每个平台的跟风列表

    private BlockingQueue<String> hotEventList; //用于接收每一次的热榜更新信息，并进行跟风狗推送

    private Reference reference;

    public HeatRecommendation(){
        recommendationLog = new ConcurrentHashMap<>();
        platformFollowDogMap = new ConcurrentHashMap<>();
        hotEventList = new ArrayBlockingQueue<>(1024);
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

        this.reference = new Reference();
    }

    private void handlerHotEvent() throws InterruptedException {
        while(true){
            String platform = hotEventList.take();
            List<FollowDog> followDogList;
            if(platformFollowDogMap.containsKey(platform)
                    &&(followDogList=platformFollowDogMap.get(platform)).size()>0){
                 //发送给爬虫队列
                for (FollowDog followDog : followDogList) {
                    String moduleName = followDog.getModuleName();
                    List<? extends Live> lives;
                    if(FollowDog.ALL_LIVES.equals(moduleName)){
                        lives = HotModuleDataCenter.DataCenter().getLiveList(platform);
                    }else{
                        lives = HotModuleDataCenter.DataCenter().getModule(platform,moduleName).getHotLives();
                    }
                    //TODO 待完善 1，需要发送弹幕爬虫请求 2，callback更改
                    for (Live live : needRecommend(lives, followDog.getBanLiver(), followDog.getTop())) {

                        Objects.requireNonNull(TaskCenter.center()).
                                request( new LiveReptileRequest(live, LiveReptileRequest.LiveType.Online,(t)->{
                            ChopperLogFactory.getLogger(LoggerType.Hot).info("成功推荐主播,结果为:T");
                        }));

                    }
                }
            }
        }
    }

    private List<Live> needRecommend(List<? extends Live> lives,List<String> banLivers,int top){
        List<Live> recommendLive = new ArrayList<>();
        int num = 0;

        for (Live live : lives) {
            if(recommendationLog.containsKey(live.getLiver())){
                if(top<num){
                    String liver = live.getLiver();
                    if (!isBan(liver,banLivers)) {
                        recommendLive.add(live);
                        num++;
                    }
                }
            }
        }
        ChopperLogFactory.getLogger(LoggerType.Hot).info("<HeatRecommend> recommend {} lives,lives info:",recommendLive);
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

    public Reference getReference(){
        return reference;
    }



    class Reference implements Runnable{

        @Override
        public void run() {
            try {
                handlerHotEvent();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
