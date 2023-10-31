package org.example.api;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.example.core.label.LabelManagerPlugin;
import org.example.pojo.VideoLabel;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author Genius
 * @date 2023/10/23 17:22
 **/
@Component
public class LabelManagerPluginApi {
    @Resource
    LabelManagerPlugin plugin;

    public List<VideoLabel> labelList(){
        return plugin.getMapper().selectList(new QueryWrapper<>());
    }

    public VideoLabel addLabel(VideoLabel label){
        try {
            label.setLabelId(UUID.randomUUID().toString());
            if (plugin.getMapper().insert(label)>0) {
                return label;
            }
        }catch (Exception e){
        }
        return null;
    }

    public boolean deleteLabel(String label){
        return plugin.getMapper().deleteByMap(Map.of("label",label))>0;
    }

    public boolean updateLabel(VideoLabel label){
        return plugin.getMapper().update(label,new QueryWrapper<VideoLabel>().eq("label_id",label.getLabelId()))>0;
    }
}
