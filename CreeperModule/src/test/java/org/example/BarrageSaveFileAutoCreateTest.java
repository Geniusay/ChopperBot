package org.example;

import org.example.exception.FileCacheException;
import org.example.pojo.configfile.BarrageSaveFile;
import org.example.pojo.download.LoadBarrageConfig;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author Genius
 * @date 2023/05/06 04:07
 **/
public class BarrageSaveFileAutoCreateTest {

    @Test
    public void test() throws FileCacheException {
        LoadBarrageConfig loadBarrageConfig = new LoadBarrageConfig("斗鱼","Post","大司马");
        BarrageSaveFile barrageSaveFile = new BarrageSaveFile(loadBarrageConfig,new ConcurrentLinkedQueue<>());
    }
}
