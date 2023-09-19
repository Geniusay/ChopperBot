package org.example.core.bgevnet.bgscore.split;

import org.example.bean.Barrage;
import org.example.core.bgevnet.bgscore.BarragePoint;
import org.example.core.bgevnet.bgscore.BarrageScoreCurvePlugin;
import org.example.core.bgevnet.bgscore.score.ScoreStrategy;

import java.util.ArrayList;
import java.util.List;

/**
 * @author vv
 * @date 2023/09/7 12:14
 **/

public class OrderSplitStrategy extends AbstractSplitStrategy{

    public OrderSplitStrategy(ScoreStrategy scoreStrategy, List<Barrage> barrages, long duration) {
        super(scoreStrategy, barrages, duration);
    }

    @Override
    public List<BarragePoint> split() {

        if(barrages==null||barrages.isEmpty()){
            return null;
        }
        int index = 0;
        int sum = 0;
        List<BarragePoint> points = new ArrayList<>();
        long bgStartTime = barrages.get(0).getTimeReal();
        long bgEndTime = barrages.get(barrages.size()-1).getTimeReal();
        long sumTime = bgEndTime - bgStartTime;
        int count = (int) (sumTime/duration);
        count+=sumTime%duration==0?0:1;
        for(int i = 0;i<count;i++){
            points.add(new BarragePoint(bgStartTime+i*duration,duration));
        }
        for (int i = 0; i < barrages.size(); i++) {
            BarragePoint point = points.get(index);
            List<String> texts = point.getBarrages();
            long startTime = bgStartTime + index*duration;
            long endTime = startTime+duration;

            Barrage barrage = barrages.get(i);
            if(barrage.getTimeReal()>endTime){
                point.setPointScore(scoreStrategy.score(texts));
                point.setBarrageNum(sum);
                point.setEndTime(barrages.get(i-1).getTimeReal());
                index++;
                sum = 0;
            }else{
                if (!BarrageScoreCurvePlugin.isBan(barrage.getContent(),liverKeywordMap)) {
                    texts.add(barrage.getContent());
                    if(sum==0){
                        point.setStartTime(barrage.getTimeReal());
                    }
                    sum++;
                    if(i==barrages.size()-1){
                        point.setPointScore(scoreStrategy.score(texts));
                        point.setBarrageNum(sum);
                        point.setEndTime(barrages.get(i-1).getTimeReal());
                    }
                }
            }
        }
        return points;
    }
}
