package org.example.core.bgevnet.bghot.hot;

import org.example.core.bgevnet.bghot.PopularRange;
import org.example.core.bgevnet.bgscore.BarragePoint;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;

/**
 * @author Genius
 * @date 2023/09/19 13:00
 **/
public class AvgPopularRangeHandler extends PopularRangeHandler{

    private int getAvg(List<BarragePoint> points) {
        int sum = 0;
        for (BarragePoint point : points) {
            sum += point.getPointScore();
        }
        return sum / points.size();
    }

    @Override
    public List<PopularRange> findRange(List<BarragePoint> points) {
        int avg = getAvg(points);
        TreeSet<PointIndex> ranges = new TreeSet<>();
        List<PopularRange> popularRanges = new ArrayList<>();
        TreeSet<String> rangeTimeSet = new TreeSet<>();
        if(points.size()==1){
            ranges.add(new PointIndex(points.get(0).getPointScore(), 0));
        }else{
            for (int i = 0; i < points.size(); i++) {
                BarragePoint point = points.get(i);
                int score = point.getPointScore();
                int next = i+1;
                int pre = i-1;
                if(i==0){
                    if(score>points.get(next).getPointScore()) ranges.add(new PointIndex(score, i));
                }
                else if(i==points.size()-1){
                   if(points.get(pre).getPointScore()>score) ranges.add(new PointIndex(score, i));
                }
                else if(score>points.get(pre).getPointScore()&&score>points.get(next).getPointScore()){
                    ranges.add(new PointIndex(score, i));
                }
            }
        }

        for (PointIndex range : ranges) {
            int index = range.getIndex();
            int nowScore = range.getScore();
            if(nowScore>=avg){
                if (!outOfMaxNum(popularRanges.size())) {
                    PopularRange popularRange = getPopularRange(points, avg, index);
                    String time = popularRange.getStartTime() + "-" + popularRange.getEndTime();
                    if(!rangeTimeSet.contains(time)) {
                        rangeTimeSet.add(time);
                        popularRanges.add(popularRange);
                    }
                }else{
                    break;
                }
            }
        }
        return popularRanges;
    }

    private PopularRange getPopularRange(List<BarragePoint> points, int avg,int index){
        int left = index-1;
        int right = index+1;
        int n = points.size();
        BarragePoint p = points.get(index);
        PopularRange popularRange = new PopularRange();

        popularRange.getList().add(p);
        popularRange.setStartTime(p.getStartTime());
        popularRange.setEndTime(p.getEndTime());
        while(left>=0||right<n){

            if(left>=0){
                BarragePoint lp = points.get(left);
                int score = lp.getPointScore();
                popularRange.getList().add(lp);
                popularRange.setStartTime(lp.getStartTime());
                if(left>0){
                    int pre_score = points.get(left - 1).getPointScore();
                    int next_score = points.get(left + 1).getPointScore();
                    if(score<=avg&&score<=pre_score&&score<=next_score){
                        left=-1;
                    }
                }
            }
            if(right<n){
                BarragePoint rp = points.get(right);
                int score = rp.getPointScore();
                popularRange.getList().add(rp);
                popularRange.setEndTime(rp.getEndTime());
                if(right<n-1){
                    int pre_score = points.get(right - 1).getPointScore();
                    int next_score = points.get(right + 1).getPointScore();
                    if(score<=avg&&score<=pre_score&&score<=next_score){
                        right=n+1;
                    }
                }
            }
            left--;
            right++;
        }
        return popularRange;
    }

}
