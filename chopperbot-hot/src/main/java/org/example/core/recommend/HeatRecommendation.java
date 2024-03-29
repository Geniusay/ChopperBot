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
import org.example.constpool.ConstPool;
import org.example.constpool.PluginName;
import org.example.core.HotModuleDataCenter;
import org.example.core.guard.HotModuleGuard;
import org.example.core.manager.CreeperGroupCenter;
import org.example.core.taskcenter.request.ReptileRequest;
import org.example.init.InitPluginRegister;
import org.example.mapper.FocusLiverMapper;
import org.example.mapper.FollowDogMapper;
import org.example.plugin.CommonPlugin;
import org.example.core.taskcenter.TaskCenter;
import org.example.plugin.SpringBootPlugin;
import org.example.service.FollowDogService;
import org.example.service.HotModuleSettingService;
import org.example.sql.annotation.SQLInit;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.regex.Pattern;

/**
 * 根据热门的直播以及看门狗设置来推荐热门直播间到系统
 */
@Data
@Component
public class HeatRecommendation extends SpringBootPlugin {

    private static final long serialVersionUID = 4749216808636623354L;
    private Map<String,CopyOnWriteArrayList<FollowDog>> platformFollowDogMap ;   //每个平台的跟风列表

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
                        platformFollowDogMap.put(module.getPlatform(),new CopyOnWriteArrayList<>());
                    }
            );

            List<FollowDog> allDog = service.getAllDog();
            for (FollowDog followDog : allDog) {
                String platform = followDog.getPlatform();
                if (platformFollowDogMap.containsKey(platform)) {
                    if(map.get(platform).getFollowDogEnable()){
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
                this.info(String.format("%s Hotspot event detected.", platform));
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
                                this.error(String.format("%s not exist the %s module!",platform,moduleName));
                                continue;
                            }
                            tempLives = HotModuleDataCenter.DataCenter().getModuleLiveList(platform, module);
                        }
                        if(tempLives==null){
                            this.error(String.format("cannot found %s %s hot lives", platform,moduleName));
                            break;
                        }
                        for (Live live : needRecommend(tempLives, getBanList(followDog.getBanLiver()), followDog.getTop())) {
                            String tempPlatform = live.getPlatform();
                            this.info("主播推荐",String.format("平台 %s,分区 %s,直播间 %s,主播 %s",
                                    tempPlatform,live.getModuleName(),live.getLiveId(),live.getLiver()),true);
                            String checkGroup = CreeperGroupCenter.getGroupName(platform, ConstGroup.LIVER_CHECKER);
                            String liveGroup = CreeperGroupCenter.getGroupName(platform,ConstGroup.LIVE_ONLINE);
                            TaskCenter taskCenter = InitPluginRegister.getPlugin(PluginName.TASK_CENTER_PLUGIN, TaskCenter.class);
                            if (CreeperGroupCenter.getFirstConfig(checkGroup)==null) {
                                taskCenter.request(new ReptileRequest((t)->{

                                },liveGroup,live));
                            }else{
                                taskCenter.request(new ReptileRequest((obj -> {
                                    obj = obj==null?live:obj;
                                    taskCenter.request(new ReptileRequest((t)->{

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

    public boolean addFollowDog(FollowDog dog){
        String platform = dog.getPlatform();
        if (hotModuleSettingService.getSetting(platform).getFollowDogEnable()) {
            List<FollowDog> followDogs = platformFollowDogMap.get(platform);
            if(followDogs==null){
                return false;
            }
            followDogs.add(dog);
            return true;
        }
        return true;
    }

    public boolean removeFollowDog(String platform,String dogId){
        List<FollowDog> followDogs = platformFollowDogMap.get(platform);
        if(followDogs!=null){
            followDogs.removeIf(dog -> {
                return dog.getDogId().equals(dogId);
            });
        }
        return true;
    }

    public boolean updateFollowDog(FollowDog dog){
        List<FollowDog> followDogs = platformFollowDogMap.get(dog.getPlatform());
        if(followDogs!=null&&!followDogs.isEmpty()){
            followDogs.removeIf(dog1 -> {return dog1.getDogId().equals(dog.getDogId());});
            followDogs.add(dog);
        }
        return true;
    }

    public boolean openPlatformFollowDog(String platform,boolean isOpen){
        HotModuleSetting setting = new HotModuleSetting();
        setting.setPlatform(platform);
        setting.setFollowDogEnable(isOpen);
        if (hotModuleSettingService.updateSetting(setting)) {
            if(isOpen){
                List<FollowDog> platformDogs = service.getPlatformDogs(platform);
                platformFollowDogMap.get(platform).addAll(platformDogs);
            }else{
                platformFollowDogMap.put(platform,new CopyOnWriteArrayList<>());
            }
            return true;
        }
        return false;
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
        if(StringUtils.hasText(banLiver)){
            String s = banLiver.substring(1, banLiver.length() - 1);
            String[] ss = s.split(",");
            return List.of(ss);
        }else{
            return  new ArrayList<>();
        }
    }

    @Override
    @SQLInit(table = "follow_dog",tableSQL = "CREATE TABLE \"follow_dog\" (\n" +
            "\t\"id\"\tINTEGER NOT NULL UNIQUE,\n" +
            "\t\"dog_id\"\tTEXT,\n" +
            "\t\"platform\"\tTEXT NOT NULL,\n" +
            "\t\"module_name\"\tTEXT NOT NULL DEFAULT 'all',\n" +
            "\t\"top\"\tINTEGER DEFAULT 5,\n" +
            "\t\"ban_liver\"\tTEXT,\n" +
            "\tPRIMARY KEY(\"id\" AUTOINCREMENT)\n" +
            ")",mapper = FollowDogMapper.class)
    public List<FollowDog> sqlInit() {
        ArrayList<FollowDog> dogs = new ArrayList<>();
        for (ConstPool.PLATFORM platform : ConstPool.PLATFORM.values()) {
            dogs.add(new FollowDog(null,UUID.randomUUID().toString(),platform.getName(),"all",1,null));
        }
        return dogs;
    }
}
