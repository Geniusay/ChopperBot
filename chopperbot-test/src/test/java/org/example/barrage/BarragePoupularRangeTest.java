package org.example.barrage;

import org.example.ConsoleApplication;
import org.example.constpool.PluginName;
import org.example.core.bgevnet.BarrageEvent;
import org.example.core.bgevnet.bghot.BarragePopularRangePlugin;
import org.example.core.bgevnet.bghot.PopularRange;
import org.example.core.bgevnet.bgscore.BarragePoint;
import org.example.core.bgevnet.bgscore.BarrageScoreCurvePlugin;
import org.example.init.InitPluginRegister;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author Genius
 * @date 2023/10/09 00:27
 **/

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ConsoleApplication.class,webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class BarragePoupularRangeTest {

    @Test
    public void Test(){
        BarrageEvent event = new BarrageEvent("douyu","online",null,null,"即将拥有人鱼线的PDD_2023-09-14 14_37_54.json");
        List<BarragePoint> points = InitPluginRegister.getPlugin(PluginName.BARRAGE_SCORE_CURVE_PLUGIN, BarrageScoreCurvePlugin.class).generateCurve(event);
        List<PopularRange> ranges = InitPluginRegister.getPlugin(PluginName.BARRAGE_POPULAR_RANGE_PLUGIN, BarragePopularRangePlugin.class).findRange(points);
        System.out.println(ranges);

    }
}
