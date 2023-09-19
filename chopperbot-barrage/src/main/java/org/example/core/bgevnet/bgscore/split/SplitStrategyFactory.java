package org.example.core.bgevnet.bgscore.split;

import org.example.bean.Barrage;
import org.example.bean.LiverKeyword;
import org.example.core.bgevnet.bgscore.score.ScoreStrategy;


import java.util.List;
import java.util.Map;

/**
 * @author Genius
 * @date 2023/09/14 00:36
 **/
public class SplitStrategyFactory {

    public static AbstractSplitStrategy build(String strType, ScoreStrategy scoreStrategy, List<Barrage> barrages, long duration, Map<String,LiverKeyword> liverKeywordMap){
        SplitStrategyType type = SplitStrategyType.valueOf(strType);
        if(SplitStrategyType.ORDER.equals(type)){
            OrderSplitStrategy orderSplitStrategy = new OrderSplitStrategy(scoreStrategy, barrages, duration);
            orderSplitStrategy.setLiverKeywordMap(liverKeywordMap);
            return orderSplitStrategy;
        }else if (SplitStrategyType.THREAD.equals(type)){
            ThreadSplitStrategy threadSplitStrategy = new ThreadSplitStrategy(scoreStrategy, barrages, duration);
            threadSplitStrategy.setLiverKeywordMap(liverKeywordMap);
            return threadSplitStrategy;
        }else{
            OrderSplitStrategy orderSplitStrategy = new OrderSplitStrategy(scoreStrategy, barrages, duration);
            orderSplitStrategy.setLiverKeywordMap(liverKeywordMap);
            return orderSplitStrategy;
        }
    }

}
