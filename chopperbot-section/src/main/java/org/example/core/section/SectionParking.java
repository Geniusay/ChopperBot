package org.example.core.section;

import org.example.bean.Barrage;
import org.example.bean.section.VideoSection;
import org.example.constpool.ConstPool;
import org.example.constpool.GlobalFileCache;
import org.example.mapper.VideoSectionMapper;
import org.example.util.JsonFileUtil;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author Genius
 * @date 2023/10/22 20:56
 **/
@Component
public class SectionParking {

    @Resource
    VideoSectionMapper mapper;

    private void saveData(VideoSection section){
        String root = (String) GlobalFileCache.ModuleSrcConfigFile.get("src", ConstPool.BARRAGE);
        String liveRoot = (String)GlobalFileCache.ModuleSrcConfigFile.get("src", ConstPool.LIVE_RECORD);
        String path = section.getVideoPath().replace(liveRoot,root);
        if (JsonFileUtil.writeJsonFile(path, Map.of("data",section.getBarrages())).isFile()) {
            section.setBarrageFile(path);
        }
    }
    public void parking(VideoSection section){
        saveData(section);
        mapper.insert(section);
    }
}
