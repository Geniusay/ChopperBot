package org.example.hot;

import org.example.ConsoleApplication;
import org.example.bean.hotmodule.HotModuleList;
import org.example.bean.live.BiliBiliLive;
import org.example.core.creeper.loadconfig.BilibiliHotLiveConfig;
import org.example.core.creeper.loadconfig.BilibiliHotModuleConfig;
import org.example.core.creeper.loadtask.BiliBiliHotLiveLoadTask;
import org.example.core.creeper.loadtask.BilibiliHotModuleLoadTask;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author Genius
 * @date 2023/09/03 15:49
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ConsoleApplication.class)
public class HotCreeperTest {

    @Test
    public void testBilibiliHotModuleCreeper(){
        BilibiliHotModuleConfig bilibiliHotModuleConfig = new BilibiliHotModuleConfig();
        HotModuleList start = new BilibiliHotModuleLoadTask(bilibiliHotModuleConfig).start();
        System.out.println(start);
    }

    @Test
    public void testBilibiliHotLiveCreeper(){
        //全热门直播
        BilibiliHotLiveConfig bilibiliHotLiveConfig = new BilibiliHotLiveConfig();
        List<BiliBiliLive> start = new BiliBiliHotLiveLoadTask(bilibiliHotLiveConfig).start();
        System.out.println(start);
        //某个模块热门直播
        BilibiliHotLiveConfig bilibiliHotLiveConfig1 = new BilibiliHotLiveConfig("2","89",1);
        List<BiliBiliLive> start1 = new BiliBiliHotLiveLoadTask(bilibiliHotLiveConfig1).start();
        System.out.println(start1);
    }
}
