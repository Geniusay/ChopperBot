package org.example.config;
/**
 * @description : [自定义弹幕评分配置]
 * @author : [Welsir]
 * @createTime : [2023/5/15 22:14]
 */

import com.alibaba.fastjson.JSON;
import org.example.entity.Anchor;
import org.example.util.FileUtil;
import org.example.util.JsonFileUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author welsir
 * @date 2023/5/15 22:14
 */
public class BarrageScoreConfig {

    private static List<Anchor> anchorList = new ArrayList<>();
    private static final HashMap<String,Integer> anchorScoreMap = new HashMap<>();

    /**
     * @description: 解析主播得分配置文件存入map
     */
    public static void getConfigFile() throws Exception {
        anchorList = JSON.parseArray(FileUtil.JSONFileToString("/anchor/score.json"), Anchor.class);
        for (Anchor anchor : anchorList) {
            anchorScoreMap.put(anchor.getName(),anchorList.indexOf(anchor));
        }
        System.out.println("anchor count ::"+anchorList.size());
    }

    /**
     * @description: 设置主播关键词并写入文件
     */
    public static void setAnchorConfig(List<Anchor> list){
        for (Anchor anchor : list) {
            //判断追加还是写入
            int idx = anchorScoreMap.get(anchor.getName())==null?0:anchorScoreMap.get(anchor.getName());
            if(idx!=0){
                List<Anchor.property> property = anchorList.get(idx).getProperty();
                property.addAll(anchor.getProperty());
            }else {
                anchorList.add(anchor);
            }
        }
        JsonFileUtil.writeJsonFile("D:\\idea_Project\\Springboot\\ChopperBot\\BarrageModule\\src\\main\\resources\\anchor\\","score.json",anchorList);
    }

    public static List<Anchor> getAnchorScoreList(){
        return anchorList;
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
