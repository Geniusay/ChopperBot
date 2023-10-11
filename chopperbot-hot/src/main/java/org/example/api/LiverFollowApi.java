package org.example.api;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import org.example.bean.FocusLiver;
import org.example.cache.FileCache;
import org.example.cache.FileCacheManager;
import org.example.config.HotModuleConfig;
import org.example.constpool.PluginName;
import org.example.core.focus.LiverFollower;
import org.example.exception.FileCacheException;
import org.example.init.InitPluginRegister;
import org.example.service.FocusLiverService;
import org.example.util.ConfigFileUtil;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author Genius
 * @date 2023/09/27 20:59
 **/

@Component
public class LiverFollowApi {

    @Resource
    LiverFollower liverFollower;

    @Resource
    FocusLiverService focusLiverService;

    public List<FocusLiver> allFocusLivers(){
        return focusLiverService.getFocusLivers();
    }

    public boolean follow(FocusLiver focusLiver){
        if (focusLiverService.addLivers(focusLiver)) {
            liverFollower.addFocusLiver(focusLiver);
            return true;
        }
        return false;
    }

    public boolean unFollow(String platform,String liver){
        if (focusLiverService.deleteLivers(platform,liver)) {
            liverFollower.deleteFocusLiver(liver);
            return true;
        }
        return false;
    }

    public void changeSetting(Map<String,Object> settings){
        ConfigFileUtil.changeSetting(settings,HotModuleConfig.getFullFilePath(),"LiverFollower");
    }

    public Object getSetting(){
        return ConfigFileUtil.getSetting(HotModuleConfig.getFullFilePath(),"LiverFollower");
    }
}
