package org.example.core.factory.videoPushFactory;

import org.example.bean.section.PackageSection;
import org.example.pojo.PacketSectionVideo;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * @Description
 * @Author welsir
 * @Date 2024/5/17 12:13
 */
public class DefaultVideoPushStrategy extends StrategyFactory {

    private List<String> priorityImportWord = new ArrayList<>();

    @Override
    public PacketSectionVideo wrapperSectionVideo(PackageSection obj) {
        if(obj != null){
            return wrapPacketSection(obj);
        }
        return null;
    }
    private PacketSectionVideo wrapPacketSection(PackageSection p){
        PacketSectionVideo video = new PacketSectionVideo();
        BeanUtils.copyProperties(p,video);
        video.setId(UUID.randomUUID().toString());
        return video;
    }

    @Override
    public List<String> queryPriority(){
        return  priorityImportWord;
    }

    @Override
    public void changePriority(List<String> newList){
        priorityImportWord = newList;
    }
}
