package org.example.util;

import com.alibaba.fastjson.JSONArray;
import org.example.bean.Barrage;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Genius
 * @date 2023/09/17 23:20
 **/
public class BarrageUtil {
    public static boolean cutBarrageFile(String inputFilePath,String outputFilePath,long start,long end){
        try {
            Map<String, Object> map = JsonFileUtil.readJsonFile(inputFilePath);
            Object barrages = map.get("data");
            if(barrages instanceof JSONArray){
                List<Barrage> list = ((JSONArray) barrages).toJavaList(Barrage.class);
                list = list.stream().sorted(Comparator.comparing(Barrage::getTimeReal)).collect(Collectors.toList());
                if(list.isEmpty()){
                    JsonFileUtil.writeJsonFile(outputFilePath,Map.of("data",list,"updateTime",TimeUtil.getNowTime_YMDHMS()));
                }else{
                    long startTime = list.get(0).getTimeReal() + start;
                    long endTime = list.get(0).getTimeReal() + end;
                    List<Barrage> newBarrageList = new ArrayList<>();
                    int endIndex = leTimeBarrageIndex(list,endTime);
                    int startIndex = geTimeBarrageIndex(list,startTime);
                    if(endIndex!=-1&&startIndex!=-1){
                        newBarrageList = List.copyOf(list.subList(startIndex,endIndex+1));
                    }
                    JsonFileUtil.writeJsonFile(outputFilePath,Map.of("data",newBarrageList,"updateTime",TimeUtil.getNowTime_YMDHMS()));
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static int leTimeBarrageIndex(List<Barrage> barrages,long time){
        int left = 0;
        int right = barrages.size() - 1;
        int result = -1;
        while (left <= right){
            int mid =  (left + right) / 2;
            Long timeReal = barrages.get(mid).getTimeReal();
            if (timeReal <= time) {
                left = mid + 1;
                result = mid;
            }else{
                right = mid - 1;
            }
        }
        return  result;
    }

    public static int geTimeBarrageIndex(List<Barrage> barrages,long time){
        int left = 0;
        int right = barrages.size() - 1;
        int result = -1;
        while (left <= right) {
            int mid = (left + right) / 2;
            Long timeReal = barrages.get(mid).getTimeReal();
            if (timeReal >= time) {
                right = mid -1;
                result = mid;
            } else {
                left = mid + 1;
            }
        }
        return result;
    }

}
