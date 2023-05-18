package org.example.config;
/**
 * @description : [自定义弹幕评分配置]
 * @author : [Welsir]
 * @createTime : [2023/5/15 22:14]
 */

import com.alibaba.fastjson.JSON;
import org.example.entity.Anchor;
import org.example.util.JsonFileUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author welsir
 * @date 2023/5/15 22:14
 */
public class BarrageScoreConfig {

    private static List<Anchor> anchorList = new ArrayList<>();
    private static HashMap<String,Integer> anchorMap = new HashMap<>();

    /**
     * @description: 解析配置文件，获取主播自定义关键词得分配置
     */
    public static void getConfigFile() throws Exception {
        String jsonStr = "";
        try (InputStream inputStream = BarrageScoreConfig.class.getResourceAsStream("/anchor/score.json");
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                jsonStr += line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        anchorList = JSON.parseArray(jsonStr, Anchor.class);
        for (Anchor anchor : anchorList) {
            anchorMap.put(anchor.getName(),anchorList.indexOf(anchor));
        }
        System.out.println(anchorList);
    }

    /**
     * @description: 设置主播关键词并写入文件
     */
    public static void setAnchorConfig(List<Anchor> list){
        for (Anchor anchor : list) {
            int idx = anchorMap.get(anchor.getName())==null?0:anchorMap.get(anchor.getName());
            if(idx!=0){
                List<Anchor.property> property = anchorList.get(idx).getProperty();
                property.addAll(anchor.getProperty());
            }else {
                anchorList.add(anchor);
            }
        }
        JsonFileUtil.writeJsonFile("D:\\idea_Project\\Springboot\\ChopperBot\\BarrageModule\\src\\main\\resources\\anchor\\","score.json",anchorList);
    }

    public static void main(String[] args) throws Exception {
        getConfigFile();

        ArrayList<Anchor> list = new ArrayList<>();
        Anchor anchor = new Anchor();
        anchor.setName("水晶哥");
        ArrayList<Anchor.property> properties = new ArrayList<>();
        properties.add(new Anchor.property("666",5));
        properties.add(new Anchor.property("下播",7));
        anchor.setProperty(properties);
        Anchor anchor1 = new Anchor();
        anchor1.setName("小团团");
        ArrayList<Anchor.property> properties1 = new ArrayList<>();
        properties1.add(new Anchor.property("666",5));
        properties1.add(new Anchor.property("下播",7));
        anchor1.setProperty(properties1);
        list.add(anchor);
        list.add(anchor1);
        setAnchorConfig(list);
    }
}
