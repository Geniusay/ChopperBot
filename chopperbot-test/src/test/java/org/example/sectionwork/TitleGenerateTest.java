package org.example.sectionwork;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.example.ConsoleApplication;
import org.example.bean.section.VideoSection;
import org.example.core.auto.video.description.DescGeneratorPlugin;
import org.example.core.auto.video.title.TitleGeneratePlugin;
import org.example.util.JsonFileUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Genius
 * @date 2023/10/22 18:28
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ConsoleApplication.class,webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class TitleGenerateTest {

    @Resource
    TitleGeneratePlugin plugin;

    @Resource
    DescGeneratorPlugin descGeneratorPlugin;
    @Test
    public void test(){
        Map<String, Object> map = JsonFileUtil.readJsonFile("E:\\Project\\ChopperBot\\config\\Barrage\\online\\huya\\Uzi_2023-10-18 21_22_57.json");
        Object data = map.get("data");
        if(data instanceof JSONArray){
            List<String> content = ((JSONArray) data).stream().map(o -> {
                if (o instanceof JSONObject) {
                    return ((JSONObject) o).get("content").toString();
                }
                return "";
            }).collect(Collectors.toList());
            VideoSection videoSection = new VideoSection();
            videoSection.setBarrages(content.subList(0,100));
            videoSection.setTag("英雄联盟");
            videoSection.setLiver("UZI");
            VideoSection process = plugin.process(videoSection);
            System.out.println(process);
            VideoSection process1 = descGeneratorPlugin.process(process);
            System.out.println(process1);
        }
    }
}
