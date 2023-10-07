package org.example.api;

import org.example.bean.FollowDog;
import org.example.core.recommend.HeatRecommendation;
import org.example.service.FollowDogService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Genius
 * @date 2023/09/27 21:32
 **/
@Component
public class HeatRecommendApi {

    @Resource
    FollowDogService followDogService;

    @Resource
    HeatRecommendation heatRecommendation;

    public boolean addFollowDog(FollowDog dog){
        if (followDogService.addFollowDog(dog)) {
            return heatRecommendation.addFollowDog(dog);
        }
        return false;
    }

    public List<FollowDog> getFollowDog(){
        return followDogService.getAllDog();
    }

    public boolean deleteFollowDog(String dogId,String platform){
        if (followDogService.deleteFollowDog(dogId)) {
            return heatRecommendation.removeFollowDog(platform,dogId);
        }
        return false;
    }

    public boolean updateFollowDog(FollowDog dog){
        if(followDogService.updateFollowDog(dog)){
            return heatRecommendation.updateFollowDog(dog);
        }
        return false;
    }

    public boolean openPlatformFollowDog(String platform,boolean isOpen){
        return heatRecommendation.openPlatformFollowDog(platform,isOpen);
    }
}
