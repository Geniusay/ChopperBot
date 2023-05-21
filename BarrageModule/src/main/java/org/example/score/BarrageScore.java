package org.example.score;
/**
 * @description : [get score by barrage-file]
 * @author : [Welsir]
 * @createTime : [2023/5/18 16:50]
 */

import com.alibaba.fastjson.JSON;
import org.example.config.BarrageFileConfig;
import org.example.config.BarrageScoreConfig;
import org.example.entity.Anchor;
import org.example.entity.Barrage;
import org.example.util.FileUtil;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author welsir
 * @date 2023/5/18 16:50
 */
public class BarrageScore {

    private static Map<String,List<Anchor.property>> anchorPropertMap = new HashMap<>(20);

    /**
     * @Description
     * @Param [filePath(相对或全路径均可), anchorName(主播名称)]
     * @return void
    **/
    public static void analysisBarrageScore(String filePath,String anchorName){
        //parse score file to get weight
        String result = FileUtil.JSONFileToString("/anchor/score.json");
        List<Anchor> objects = JSON.parseArray(result,Anchor.class);
        anchorPropertMap = objects.stream().collect(Collectors.toMap(Anchor::getName, Anchor::getProperty, (existing, replacement) -> existing));
        //call the barrageScoreConfig to get barrage file
        HashMap map = BarrageFileConfig.getBarrageFile();
        System.out.println("map size::"+map.size());
        //Traverse the map set to sort timeIdx
        map.forEach((k,v)->{
            System.out.println(k);
            String anchor = splitFileName((String) k);
            ((List<Barrage>) v).sort(new BarrageComparator());
            List<Anchor> anchorScoreList = BarrageScoreConfig.getAnchorScoreList();
            for (Anchor anchor1 : anchorScoreList) {
                if (anchor1.getName().equals(anchor)) {
                    
                }
            }
        });
        System.out.println(anchorPropertMap);
    }

    private static String splitFileName(String fileName){
        int index = fileName.indexOf("-");
        if(index!=-1){
            String substring = fileName.substring(0, index);
            System.out.println(substring);
            return substring;
        }else {
            throw new RuntimeException("fileName error");
        }
    }

    static class BarrageComparator implements Comparator<Barrage> {
        @Override
        public int compare(Barrage b1, Barrage b2) {
            // 根据timeIndex字段进行比较
            return b1.getTimeIndex().compareTo(b2.getTimeIndex());
        }
    }
    
    public static void main(String[] args) {
        analysisBarrageScore("D:\\idea_Project\\Springboot\\ChopperBot\\BarrageModule\\src\\main\\resources\\anchor\\score.json","");
    }
}
