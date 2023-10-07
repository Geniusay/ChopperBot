package org.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.bean.FocusLiver;
import org.example.bean.FollowDog;
import org.example.mapper.FocusLiverMapper;
import org.example.mapper.FollowDogMapper;
import org.example.service.FocusLiverService;
import org.example.service.FollowDogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author Genius
 * @date 2023/09/10 22:19
 **/
@Service
public class FollowDogServiceImpl extends ServiceImpl<FollowDogMapper, FollowDog> implements FollowDogService {

    @Resource
    FollowDogMapper mapper;

    @Override
    public List<FollowDog> getAllDog() {
        return query().list();
    }

    @Override
    public List<FollowDog> getPlatformDogs(String platform) {
        return  query().eq("platform",platform).list();
    }

    @Override
    public boolean addFollowDog(FollowDog dog) {
        return mapper.insert(dog)==1;
    }

    @Override
    public boolean updateFollowDog(FollowDog dog) {
        return update().eq("dog_id",dog.getDogId()).update(dog);
    }

    @Override
    public boolean deleteFollowDog(String dog_id) {
        return mapper.deleteByMap(Map.of("dog_id",dog_id))==1;
    }
}
