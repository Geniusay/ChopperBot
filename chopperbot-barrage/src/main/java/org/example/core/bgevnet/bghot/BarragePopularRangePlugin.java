package org.example.core.bgevnet.bghot;

import org.example.core.bgevnet.bghot.hot.AvgPopularRangeHandler;
import org.example.core.bgevnet.bghot.hot.PopularRangeHandler;
import org.example.core.bgevnet.bgscore.BarragePoint;
import org.example.plugin.SpringBootPlugin;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Genius
 * @date 2023/09/14 18:55
 **/
@Component
public class BarragePopularRangePlugin extends SpringBootPlugin {

    private String popularHandler = "AvgHotHandler";

    private PopularRangeHandler handler;

    @Override
    public boolean init() {
        if(popularHandler.equals("AvgHotHandler")){
            handler = new AvgPopularRangeHandler();
        }else{
            this.error("unknown hot handler: " + popularHandler + ", use AvgHotHandler instead!");
            return false;
        }
        return super.init();
    }

    public List<PopularRange> findRange(List<BarragePoint> points){
        return handler.findRange(points);
    }
}
