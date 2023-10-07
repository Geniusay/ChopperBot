package org.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.bean.FocusLiver;
import org.example.mapper.FocusLiverMapper;
import org.example.service.FocusLiverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author Genius
 * @date 2023/09/09 20:03
 **/
@Service
public class FocusLiverServiceImpl extends ServiceImpl<FocusLiverMapper,FocusLiver> implements FocusLiverService {

    @Resource
    FocusLiverMapper mapper;

    @Override
    public List<FocusLiver> getFocusLivers() {
        return query().list();
    }

    @Override
    public boolean deleteLivers(String platform,String liver) {
        return mapper.deleteByMap(
                Map.of(
                        "platform",platform,
                        "liver",liver)
        )==1;
    }

    @Override
    public boolean addLivers(FocusLiver liver) {
        if(hasLivers(liver.getLiver(),liver.getRoomId())){
            return false;
        }
        return mapper.insert(liver)==1;
    }

    public boolean hasLivers(String liver,String roomId){
        return query().eq("liver",liver).eq("room_id",roomId).count()==1;
    }
}
