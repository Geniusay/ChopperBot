package org.example.api;

import org.example.bean.FocusLiver;
import org.example.core.focus.LiverFollower;
import org.example.service.FocusLiverService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

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
}
