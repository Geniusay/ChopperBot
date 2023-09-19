package org.example.core.bgevnet.bgscore.score;

import org.example.bean.LiverKeyword;
import org.example.core.bgevnet.bgscore.BarrageScoreCurvePlugin;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @author Genius
 * @date 2023/09/13 23:58
 **/
//根据配置文件和基础分数来进行分数统计

public class ScoringStrategy extends AbstractScoreStrategy{

    private Map<String, LiverKeyword> liverKeyWordMap;

    public ScoringStrategy(Map<String, LiverKeyword> liverKeyWordMap) {
        this.liverKeyWordMap = liverKeyWordMap;
    }
    @Override
    public int score(List<String> list) {
        int sum = 0;
        for (String s : list) {
            sum+=getScore(s);
        }
        return sum;
    }

    private int getScore(String barrage, Map<String, LiverKeyword> map){
        if(map==null||map.isEmpty()){
            return BarrageScoreCurvePlugin.basicBarrageScore;
        }
        if (map.containsKey(barrage)) {
            return map.get(barrage).getScore();
        }else{
            for (Map.Entry<String,LiverKeyword> entry : map.entrySet()) {
                if (Pattern.matches(entry.getKey(),barrage)) {
                    return entry.getValue().getScore();
                }
            }
        }
        return BarrageScoreCurvePlugin.basicBarrageScore;
    }

    private int getScore(String barrage){
        return Math.max(getScore(barrage,BarrageScoreCurvePlugin.globalKeywordMap),getScore(barrage,liverKeyWordMap));
    }
}
