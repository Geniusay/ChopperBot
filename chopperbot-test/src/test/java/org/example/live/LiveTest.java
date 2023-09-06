package org.example.live;

import org.example.ConsoleApplication;
import org.example.core.creeper.loadconfig.BilibiliLiveOnlineConfig;
import org.example.core.creeper.loadconfig.DouyuLiveOnlineConfig;
import org.example.core.manager.LiveDownloadManager;
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
        LiveDownloadManager liveDownLoadManager = new LiveDownloadManager(5);
        DouyuLiveOnlineConfig douyuLiveConfig = new  DouyuLiveOnlineConfig(
                "793400", "C:\\Users\\admin\\Desktop\\douyu", "CF", true,0
        );
        try {
            // 向任务管理器中添加任务
            String taskId = liveDownLoadManager.addTask(douyuLiveConfig);  // 获取任务的标识符
            int cnt = 100;
            while (cnt > 0) {
                cnt--;
                Thread.sleep(1000);
            }

            liveDownLoadManager.terminateThenSave(douyuLiveConfig,taskId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void BilibiliLive(){
        BilibiliLiveOnlineConfig liveConfig = new BilibiliLiveOnlineConfig("732", "E:\\Project\\ChopperBot\\config\\LiveRecord\\", "猪猪公主",false);

        // 创建下载任务管理器
        LiveDownloadManager liveDownLoadManager = new LiveDownloadManager(5);

        try {
            // 向任务管理器中添加任务
            String taskId = liveDownLoadManager.addTask(liveConfig);  // 获取任务的标识符
            int cnt = 600;
            while (cnt > 0) {
                cnt--;
                Thread.sleep(1000);
            }

            liveDownLoadManager.terminateThenSave(liveConfig,taskId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
