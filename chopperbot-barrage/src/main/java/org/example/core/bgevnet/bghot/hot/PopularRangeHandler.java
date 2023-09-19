package org.example.core.bgevnet.bghot.hot;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.core.bgevnet.bghot.PopularRange;
import org.example.core.bgevnet.bgscore.BarragePoint;

import java.util.List;

/**
 * @author Genius
 * @date 2023/09/19 01:13
 **/
public abstract class PopularRangeHandler {

    protected int maxSplitNum = 5;

    protected long minVideoTime = -1;

    protected long maxVideoTime = -1;

    protected boolean needDrop = false;


    public abstract List<PopularRange> findRange(List<BarragePoint> points);

    @AllArgsConstructor
    @Data
    class PointIndex implements Comparable<PointIndex>{
        private int score;
        private int index;

        @Override
        public int compareTo(PointIndex o) {
            return o.score-this.score;
        }
    }

    public boolean outOfMaxNum(int num){
        return num >= maxSplitNum;
    }

}
