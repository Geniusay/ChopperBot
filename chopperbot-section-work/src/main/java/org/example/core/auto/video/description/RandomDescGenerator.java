package org.example.core.auto.video.description;

import org.example.util.MapUtil;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @author Genius
 * @date 2023/10/25 21:17
 **/
@Component
public class RandomDescGenerator extends DescGenerator{

    @Override
    public List<?> sqlInit() {
        return null;
    }

    @Override
    public void preGenerate() {

    }

    @Override
    public String generate(Map<String, Object> data) {
        try {
            String liver = MapUtil.getString(data,"liver");
            return String.format("%s直播精彩集锦",liver);
        } catch (Exception e){
            return "";
        }
    }
}
