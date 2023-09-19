package org.example.core.bgevnet.bgscore.split;

import lombok.Data;
import org.example.bean.Barrage;
import org.example.core.bgevnet.bgscore.BarragePoint;
import org.example.core.bgevnet.bgscore.BarrageScoreCurvePlugin;
import org.example.core.bgevnet.bgscore.score.ScoreStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Genius
 * @date 2023/09/13 22:28
 **/
@Data
public class ThreadSplitStrategy extends AbstractSplitStrategy{

    private ExecutorService spiltWorker;

    private AtomicInteger index = new AtomicInteger(0);

    private AtomicInteger barrageIndex = new AtomicInteger(0);

    private List<BarragePoint> list;

    private CountDownLatch countDownLatch;

    private long barrageStartTime;

    private int nThread;

    public ThreadSplitStrategy(ScoreStrategy scoreStrategy, List<Barrage> barrages, long duration) {
        super(scoreStrategy, barrages, duration);
        this.nThread = 4;
        spiltWorker  = Executors.newFixedThreadPool(nThread);
        list = null;
    }

    private void initList(long bgStartTime,int count){
        list = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            list.add(new BarragePoint(bgStartTime+i*duration,duration));
        }
    }

    @Override
    public List<BarragePoint> split() {
        if(barrages.size()>=2){
            long sumTime = barrages.get(barrages.size()-1).getTimeReal()-barrages.get(0).getTimeReal();
            int count = (int) (sumTime/duration)+(sumTime%duration==0?0:1);
            this.barrageStartTime = barrages.get(0).getTimeReal();
            initList(barrageStartTime,count);
            countDownLatch = new CountDownLatch(nThread);

            for(int i=0;i<nThread;i++){
                spiltWorker.submit(new Divisor());
            }
            try {
                countDownLatch.await();
            }catch (InterruptedException e){
                return list;
            }
        }
        return list;
    }

    class Divisor implements Runnable{
        @Override
        public void run() {
            int nowIndex;
            int n = barrages.size();
            while((nowIndex = index.getAndIncrement())<list.size()){
                int bgIndex = barrageIndex.get();
                long startTime = barrageStartTime + nowIndex*duration;
                long endTime = startTime + duration;
                int barrageSum = 0;
                BarragePoint point = list.get(nowIndex);
                List<String> stringList = point.getBarrages();
                for(int i=bgIndex;i<n;i++){
                    Barrage barrage = barrages.get(i);
                    long barrageTimeReal = barrage.getTimeReal();
                    if(barrageTimeReal>endTime){
                        break;
                    }else if(barrageTimeReal>=startTime){
                        if(!BarrageScoreCurvePlugin.isBan(barrage.getContent(),liverKeywordMap)){
                            stringList.add(barrage.getContent());
                            if(barrageSum==0){
                                point.setStartTime(barrage.getTimeReal());
                            }
                            point.setEndTime(barrage.getTimeReal());
                            barrageSum++;
                        }
                    }
                }
                point.setBarrageNum(barrageSum);
                point.setPointScore(scoreStrategy.score(stringList));
                barrageIndex.addAndGet(barrageSum);
            }
            countDownLatch.countDown();
        }
    }

}
