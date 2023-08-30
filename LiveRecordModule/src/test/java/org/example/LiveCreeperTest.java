package org.example;

import org.example.core.creeper.loadconfig.BilibiliLiveOnlineConfig;
import org.example.core.creeper.loadconfig.DouyuLiveOnlineConfig;
import org.example.core.manager.TaskManager;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Genius
 * @date 2023/08/31 00:40
 **/
public class LiveCreeperTest {

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
        BilibiliLiveOnlineConfig liveConfig = new BilibiliLiveOnlineConfig("732", "E:\\Project\\ChopperBot\\config\\LiveRecord\\", "猪猪公主",true);

        // 创建下载任务管理器
        TaskManager taskManager = new TaskManager(5);

        try {
            // 向任务管理器中添加任务
            String taskId = taskManager.addTask(liveConfig);  // 获取任务的标识符
            taskManager.showDownloadTable(taskId);
            int cnt = 100;
            while (cnt > 0) {
                cnt--;
                Thread.sleep(1000);
            }

            taskManager.terminateThenSave(liveConfig,taskId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
