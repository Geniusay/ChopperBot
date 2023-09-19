package org.example.core.bgevnet.bghot;

import lombok.Data;
import org.example.core.bgevnet.bgscore.BarragePoint;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Genius
 * @date 2023/09/19 13:04
 **/
@Data
public class PopularRange {
    private List<BarragePoint> list;

    private long startTime;

    private long endTime;

    public PopularRange(List<BarragePoint> list) {
        this.list = list;
    }

    public PopularRange() {
        list = new ArrayList<>();
    }

    public List<String> allText(){
        ArrayList<String> allText = new ArrayList<>();
        for (BarragePoint point : list) {
            allText.addAll(point.getBarrages());
        }
        return allText;
    }

    public void addBarragePoint(BarragePoint barragePoint){
        list.add(barragePoint);
    }

}
