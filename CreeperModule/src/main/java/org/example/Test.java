package org.example;

import org.example.core.manager.LoadTaskManager;
import org.example.pojo.download.assign.LoadConfig_R_Douyu;

public class Test {

    public static void main(String[] args) throws Exception {
        // 创建一个任务管理器
        LoadTaskManager manager = new LoadTaskManager();
        // 创建一个斗鱼录播的配置类
        LoadConfig_R_Douyu dsm = new LoadConfig_R_Douyu("大司马", "Kp1QM8gb4ow7k4bj");
        // 创建一个任务,返回一个唯一的key
        String dsmTask = manager.addTask(dsm);
        // 开启此次下载任务
        manager.startTask(dsmTask);

        for (int i = 0; i < 10; i++) {
            Thread.sleep(1000);
            System.out.println(dsmTask + "(运行状态):" + manager.isTaskRunning(dsmTask));
            System.out.println(dsmTask + "(缓存中弹幕条数):" + manager.getCacheSize(dsmTask));
        }
        // 刷入数据到其他地方去
        System.out.println(dsmTask + "(刷入数据):" + manager.flushTaskCacheAndSave(dsmTask));

        for (int i = 0; i < 10; i++) {
            Thread.sleep(1000);
            System.out.println(dsmTask + "(运行状态):" + manager.isTaskRunning(dsmTask));
            System.out.println(dsmTask + "(缓存中弹幕条数):" + manager.getCacheSize(dsmTask));
        }
        // 刷入数据到其他地方去
        System.out.println(dsmTask + "(刷入数据):" + manager.flushTaskCacheAndSave(dsmTask));

        // 结束掉任务
        manager.endTask(dsmTask);

        for (int i = 0; i < 10; i++) {
            Thread.sleep(1000);
            System.out.println(dsmTask + "(运行状态):" + manager.isTaskRunning(dsmTask));
            System.out.println(dsmTask + "(缓存中弹幕条数):" + manager.getCacheSize(dsmTask));
        }
    }
}
