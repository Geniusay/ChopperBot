package org.example;

import org.apache.poi.ss.formula.functions.T;
import org.example.core.manager.TaskManager;
import org.example.pojo.live.DouyuLiveConfig;


public class DouyuLiveTest {
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager(5);

        DouyuLiveConfig douyuLiveConfig = new DouyuLiveConfig("3637778", "C:\\Users\\admin\\Desktop\\douyu\\", "DouyuLiveOnline");
        try {
            // 向任务管理器中添加任务
            String taskId = taskManager.addTask(douyuLiveConfig);  // 获取任务的标识符

            int cnt = 10;
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
