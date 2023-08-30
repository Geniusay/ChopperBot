package org.example.live;

import org.example.ConsoleApplication;
import org.example.core.creeper.loadconfig.BilibiliLiveOnlineConfig;
import org.example.core.creeper.loadconfig.DouyuLiveOnlineConfig;
import org.example.core.manager.TaskManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Genius
 * @date 2023/08/31 01:16
 **/

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ConsoleApplication.class)
public class LiveTest {
    @Test
    public void DouyuLive(){
        TaskManager taskManager = new TaskManager(5);
        DouyuLiveOnlineConfig douyuLiveConfig = new  DouyuLiveOnlineConfig(
                "4333872", "E:\\Project\\ChopperBot\\config\\LiveRecord\\", "CF", true
        );
        try {
            // 向任务管理器中添加任务
            String taskId = taskManager.addTask(douyuLiveConfig);  // 获取任务的标识符
            taskManager.showDownloadTable(taskId);
            int cnt = 100;
            while (cnt > 0) {
                cnt--;
                Thread.sleep(1000);
            }

            taskManager.terminateThenSave(douyuLiveConfig,taskId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void BilibiliLive(){
        BilibiliLiveOnlineConfig liveConfig = new BilibiliLiveOnlineConfig("732", "E:\\Project\\ChopperBot\\config\\LiveRecord\\", "猪猪公主",false);

        // 创建下载任务管理器
        TaskManager taskManager = new TaskManager(5);

        try {
            // 向任务管理器中添加任务
            String taskId = taskManager.addTask(liveConfig);  // 获取任务的标识符
            taskManager.showDownloadTable(taskId);
//            int cnt = 20;
//            while (cnt > 0) {
//                cnt--;
//                Thread.sleep(1000);
//            }
//
//            taskManager.terminateThenSave(liveConfig,taskId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
