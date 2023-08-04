package org.example;

import org.example.core.manager.TaskManager;
import org.example.pojo.live.BilibiliLiveConfig;

public class LiveStreamTest {
    private static final int THREAD_NUM = 10;

    public static void main(String[] args) {
        // 创建直播配置
        BilibiliLiveConfig liveConfig = new BilibiliLiveConfig("732", "D:\\", "猪猪公主");

        // 创建下载任务管理器
        TaskManager taskManager = new TaskManager(THREAD_NUM);

        try {
            // 向任务管理器中添加任务
            String taskId = taskManager.addTask(liveConfig);  // 获取任务的标识符

            int cnt = 20;
            while (cnt > 0) {
                cnt--;
                if (taskManager.isConnectionClosed(taskId)) {
                    System.out.println("连接中断，已停止录制...");
                    break;
                }
                System.out.println("平均下载速度：" + taskManager.getDownloadSpeedAvg(taskId) + " B/s");
                System.out.println("瞬时下载速度：" + taskManager.getDownloadSpeed(taskId) + " B/s");
                System.out.println("已写入数据量：" + taskManager.getDownloadedBytes(taskId) + " bytes");
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



