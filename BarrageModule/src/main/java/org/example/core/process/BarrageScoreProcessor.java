package org.example.core.process;

import org.assertj.core.internal.bytebuddy.build.Plugin;
import org.example.constpool.PluginName;
import org.example.core.listen.BarrageFileMonitor;
import org.example.init.InitPluginRegister;
import org.example.plugin.CommonPlugin;
import org.example.pojo.Anchor;
import org.example.pojo.Barrage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description Keyword Score Calculator
 * @Author welsir
 * @Date 2023/8/4 21:01
 */
public class BarrageScoreProcessor {

    private volatile static BarrageScoreProcessor barrageScoreProcessor;
    private static Map<String,int []> preFixSumMap;
    private static Map<String,List<String>> anchorBarrageMap;
    private int[] prefixSum;
    private Long startTime;
    private Long endTime;
    private BarrageScoreProcessor() {
    }

    public static BarrageScoreProcessor getInstance() {
        if (barrageScoreProcessor == null) {
            synchronized (BarrageScoreProcessor.class) {
                if (barrageScoreProcessor == null) {
                    preFixSumMap = new HashMap<>();
                    barrageScoreProcessor = new BarrageScoreProcessor();
                }
            }
        }
        return barrageScoreProcessor;
    }

    public void calculateBarrageListScore(String anchorFileName,String barrageFileName){

        BarrageFileMonitor plugin = (BarrageFileMonitor)InitPluginRegister.getPlugin(PluginName.BARRAGE_FILE_PLUGIN);
        //获取主播关键词得分map
        Map<String, Integer> barrageScoreMap = ((Map<String,Map<String,Integer>>)plugin.getAnchorBarrageMap()).get(anchorFileName);
        //获取弹幕列表
        Map<String,List<Barrage>> barrageMap = (Map<String,List<Barrage>>) plugin.getBarrageMap();
        List<Barrage> barrageList = barrageMap.get(barrageFileName);
        this.startTime=barrageList.get(0).getTimeIndex();
        this.endTime=barrageList.get(barrageList.size()-1).getTimeIndex();
        prefixSum = new int[(int) (endTime-startTime)];

        for (int i = 1; i <= barrageList.size(); i++) {
            Barrage barrage = barrageList.get(i - 1);
            int score = barrageScoreMap.getOrDefault(barrage.getContent(), 0);
            prefixSum[i] = prefixSum[i - 1] + score;
        }
        preFixSumMap.put(barrageFileName,prefixSum);
        anchorBarrageMap.computeIfAbsent(anchorFileName, k -> new ArrayList<>()).add(barrageFileName);
    }
    public int[] getScore(int intervalNumber,String barrageFileName) {
        int[] score = preFixSumMap.get(barrageFileName);
        int steps = (int) Math.ceil((double) score.length / intervalNumber);

        // 计算新数组的长度
        int newLength = (int) Math.ceil((double) score.length / steps);
        int[] result = new int[newLength];

        // 将前缀和数组拆分为子数组，并计算得分和
        for (int i = 0; i < newLength; i++) {
            int startIndex = i * steps;
            int endIndex = Math.min((i + 1) * steps, score.length);
            result[i] = score[endIndex - 1] - (startIndex > 0 ? score[startIndex - 1] : 0);
        }
        return result;
    }

}
