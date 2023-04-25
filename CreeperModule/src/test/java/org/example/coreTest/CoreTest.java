package org.example.coreTest;

import org.example.core.control.LoadTask;
import org.example.core.factory.TaskFactory;
import org.example.core.manager.LoadTaskManager;
import org.example.pojo.download.assign.LoadConfig_R_Douyu;
import org.junit.jupiter.api.Test;

/**
 * @author 燧枫
 * @date 2023/4/25 15:30
*/
public class CoreTest {

    @Test
    public void HelloWorld() throws InterruptedException {
        // 创建一个任务管理器
        LoadTaskManager manager = new LoadTaskManager();
        // 创建一个斗鱼录播的配置类
        LoadConfig_R_Douyu dsm = new LoadConfig_R_Douyu("大司马", "Kp1QM8gb4ow7k4bj");
        // 创建一个任务,返回一个唯一的key
        String dsmTask = manager.addTask(dsm);
        // 开启此次下载任务
        manager.startTask(dsmTask);

        for (int i = 0; i < 20; i++) {
            Thread.sleep(1000);
            System.out.println(dsmTask + "(运行状态):" + manager.isTaskRunning(dsmTask));
            System.out.println(dsmTask + "(缓存中弹幕条数):" + manager.getCacheSize(dsmTask));
        }
        // 刷入数据到其他地方去
        System.out.println(dsmTask + "(刷入数据):" + manager.flushTaskCacheAndSave(dsmTask));

        for (int i = 0; i < 20; i++) {
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

    @Test
    public void testTask() throws InterruptedException {
        LoadConfig_R_Douyu dsm = new LoadConfig_R_Douyu("大司马", "Kp1QM8gb4ow7k4bj");
        TaskFactory taskFactory = new TaskFactory();
        LoadTask loadTask = taskFactory.getLoadTask(dsm);
        loadTask.start();

        for (int i = 0; i < 10; i++) {
            Thread.sleep(1000);
            System.out.println(loadTask.isRunning());
            System.out.println(loadTask.getCacheSize());
        }

        loadTask.end();

        for (int i = 0; i < 10; i++) {
            Thread.sleep(1000);
            System.out.println(loadTask.isRunning());
            System.out.println(loadTask.getCacheSize());
        }

    }

    @Test
    public void testTaskManage() throws InterruptedException {
        LoadConfig_R_Douyu dsm = new LoadConfig_R_Douyu("大司马", "Kp1QM8gb4ow7k4bj");
        LoadConfig_R_Douyu swk = new LoadConfig_R_Douyu("孙悟空", "X3JzMaObqDYvPQro");
        LoadTaskManager manager = new LoadTaskManager();
        String damKey = manager.addTask(dsm);
        String swkKey = manager.addTask(swk);
        manager.startTask(damKey);
        manager.startTask(swkKey);

        System.out.println(damKey);

        for (int i = 0; i < 20; i++) {
            Thread.sleep(1000);
            System.out.println(damKey + "(运行状态):" + manager.isTaskRunning(damKey));
            System.out.println(damKey + "(弹幕条数):" + manager.getCacheSize(damKey));
            System.out.println(swkKey + "(运行状态):" + manager.isTaskRunning(swkKey));
            System.out.println(swkKey + "(弹幕条数):" + manager.getCacheSize(swkKey));
        }

        System.out.println(damKey + "(刷入数据):" + manager.flushTaskCacheAndSave(damKey));
        System.out.println(swkKey + "(刷入数据):" + manager.flushTaskCacheAndSave(swkKey));

        manager.endTask(damKey);

        for (int i = 0; i < 20; i++) {
            Thread.sleep(1000);
            System.out.println(damKey + "(运行状态):" + manager.isTaskRunning(damKey));
            System.out.println(damKey + "(弹幕条数):" + manager.getCacheSize(damKey));
            System.out.println(swkKey + "(运行状态):" + manager.isTaskRunning(swkKey));
            System.out.println(swkKey + "(弹幕条数):" + manager.getCacheSize(swkKey));
        }

        manager.taskFinished(damKey);
    }
}
