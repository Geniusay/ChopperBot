package org.example;

import org.example.core.manager.LoadTaskManager;
import org.example.exception.FileCacheException;
import org.example.init.ChopperBotConfigFileInitMachine;
import org.example.pojo.download.assign.BilibiliLiveLoadBarrageConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Genius
 * @date 2023/08/06 13:50
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ConsoleApplication.class)
public class CreeperBarrageTest {


    @Test
    public void douyu() throws FileCacheException, InterruptedException {
        LoadTaskManager manager = new LoadTaskManager();
        // 创建一个B站直播的配置类
        BilibiliLiveLoadBarrageConfig zzgz = new BilibiliLiveLoadBarrageConfig("猪猪公主", "6154037");
        // 创建一个任务,返回一个唯一的key
        String task = manager.creatTask(zzgz);
        // 开启此次下载任务
        manager.startTask(task);

        for (int i = 0; i < 20; i++) {
            Thread.sleep(1000);
            System.out.println(task + "(运行状态):" + manager.isTaskRunning(task));
            System.out.println(task + "(缓存中弹幕条数):" + manager.getCacheSize(task));
        }
        // 刷入数据到其他地方去
        System.out.println(task + "(刷入数据):" + manager.flushTaskCacheAndSave(task));

        for (int i = 0; i < 20; i++) {
            Thread.sleep(1000);
            System.out.println(task + "(运行状态):" + manager.isTaskRunning(task));
            System.out.println(task + "(缓存中弹幕条数):" + manager.getCacheSize(task));
        }
        // 刷入数据到其他地方去
        System.out.println(task + "(刷入数据):" + manager.flushTaskCacheAndSave(task));

        // 结束掉任务
        manager.endTask(task);

        for (int i = 0; i < 20; i++) {
            Thread.sleep(1000);
            System.out.println(task + "(运行状态):" + manager.isTaskRunning(task));
            System.out.println(task + "(缓存中弹幕条数):" + manager.getCacheSize(task));
        }
    }
}
