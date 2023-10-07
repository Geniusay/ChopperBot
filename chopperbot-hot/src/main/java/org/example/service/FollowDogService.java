package org.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.bean.FollowDog;

import java.util.List;
import java.util.UUID;

public interface FollowDogService extends IService<FollowDog> {

    List<FollowDog> getAllDog();

    List<FollowDog> getPlatformDogs(String platform);

    boolean addFollowDog(FollowDog dog);

    boolean updateFollowDog(FollowDog dog);

    boolean deleteFollowDog(String dog_id);

}
