package org.example.barrage;

import org.example.ConsoleApplication;
import org.example.core.creeper.loadconfig.BilibiliLiveLoadBarrageConfig;
import org.example.core.creeper.loadtask.BilibiliBarrageLiveLoadTask;
import org.example.pojo.Barrage;
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
@SpringBootTest(classes = ConsoleApplication.class)
public class BarrageCreeperTest {

    @Test
    public void testBilibiliBarrageCreeper(){
        BilibiliLiveLoadBarrageConfig config = new BilibiliLiveLoadBarrageConfig("猪猪女孩", "71002");
        List<? extends Barrage> start = new BilibiliBarrageLiveLoadTask(config).start();

    }
}
