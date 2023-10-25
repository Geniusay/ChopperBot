package org.example.core.auto.video.title;

import com.alibaba.fastjson.JSONObject;
import org.example.bean.section.PackageSection;
import org.example.bean.section.VideoSection;
import org.example.constpool.PluginName;
import org.example.core.gpt.ChatGPTMsgBuilder;
import org.example.core.gpt.OpenAPIPlugin;
import org.example.plugin.PluginCheckAndDo;
import org.example.pojo.GPTKey;
import org.example.pojo.TitleScheme;
import org.example.util.ClassUtil;
import org.example.util.MapUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author Genius
 * @date 2023/10/25 20:17
 **/
@Component
public class RandomTitleGenerator extends TitleGenerator{

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
            Object barrages = data.get("barrages");
            Object labels = data.get("labels");
            if(barrages instanceof List){
                String barrage = ((List<?>) barrages).get((int) (Math.random() * ((List) barrages).size())).toString();
                String liver = MapUtil.getString(data,"liver");
                String tag = MapUtil.getString(data,"tag");
                String label = "";
                if(labels instanceof List){
                    label = ((List<?>) labels).get((int) (Math.random() * ((List) labels).size())).toString();
                }
                return String.format("【%s】%s%s直播，弹幕直呼:%s!!",tag,liver,label,barrage);
            }
        } catch (Exception e){
            return "";
        }
        return "";
    }
}
