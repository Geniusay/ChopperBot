package org.example.core.bgevnet.bgscore.score;

import org.example.bean.LiverKeyword;

import java.util.Map;

/**
 * @author Genius
 * @date 2023/09/14 00:36
 **/
public class ScoreStrategyFactory {

    public static AbstractScoreStrategy build(String strType,Map<String , LiverKeyword> map){
        ScoreStrategyType type = ScoreStrategyType.valueOf(strType);
        if(ScoreStrategyType.SCORING.equals(type)){
            return new ScoringStrategy(map);
        }else if (ScoreStrategyType.COUNT.equals(type)){
            return new CountScoreStrategy();
        }else{
            return new ScoringStrategy(map);
        }
    }

}
