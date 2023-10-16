package org.example.barrage;

import org.example.ConsoleApplication;
import org.example.bean.barrage.BilibiliBarrage;
import org.example.bean.barrage.HuyaBarrage;
import org.example.core.creeper.loadconfig.BilibiliLiveLoadBarrageConfig;
import org.example.core.creeper.loadconfig.HuyaLiveBarrageLoadConfig;
import org.example.core.creeper.loadtask.BilibiliLiveBarrageLoadTask;
import org.example.core.creeper.loadtask.HuyaLiveBarrageLoadTask;
import org.example.exception.FileCacheException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author Genius
 * @date 2023/09/03 19:54
 **/

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ConsoleApplication.class,webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class BarrageCreeperTest {

    @Test
    public void testBilibiliBarrageCreeper(){
        BilibiliLiveLoadBarrageConfig config = new BilibiliLiveLoadBarrageConfig("某幻君", "271744");
        List<BilibiliBarrage> start = new BilibiliLiveBarrageLoadTask(config).start();

    }

    @Test
    public void testHuyaBarrageCreeper(){
        HuyaLiveBarrageLoadConfig config = new HuyaLiveBarrageLoadConfig("123", "243547");
        try {
            List<HuyaBarrage> start = new HuyaLiveBarrageLoadTask(config).start();
        } catch (FileCacheException e) {
            e.printStackTrace();
        }
    }
}
