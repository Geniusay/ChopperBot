package org.example.core.manager;

import org.example.core.component.LiveStreamTask;
import org.example.core.component.StatusMonitor;
import org.example.core.factory.LiveTaskFactory;
import org.example.pojo.live.LiveConfig;
import org.example.utils.VideoConverter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 下载任务管理器
 * @author 燧枫
 * @date 2023/5/19 17:12
 */
public class TaskManager {

    private ExecutorService executor;
    private Map<String, Future<?>> futures;
    private Map<String, LiveStreamTask> tasks;
    private LiveTaskFactory taskFactory;
    private Map<String, StatusMonitor> statusMonitors;

    public TaskManager(int maxTasks) {
        this.executor = Executors.newFixedThreadPool(maxTasks);
        this.futures = new HashMap<>();
        this.tasks = new HashMap<>();
        this.taskFactory = new LiveTaskFactory();
        this.statusMonitors = new HashMap<>();
    }

    public String addTask(LiveConfig liveConfig) throws FileNotFoundException {
        LiveStreamTask task = this.taskFactory.create(liveConfig);
        if (task == null) {
            throw new IllegalArgumentException("Unable to create task for live config: " + liveConfig);
        }

        StatusMonitor statusMonitor = new StatusMonitor();
        String taskId = System.currentTimeMillis() + liveConfig.getRoomId();  // 创建唯一的任务标识符
        statusMonitors.put(taskId, statusMonitor);
        tasks.put(taskId, task);

        OutputStream fileIO = new FileOutputStream(liveConfig.getVideoPath() + liveConfig.getRoomId() + ".flv");

        Future<?> future = executor.submit(() -> {
            task.start(executor, statusMonitor, fileIO);
        });
        futures.put(taskId, future);

        return taskId;  // 返回任务的标识符
    }

    public LiveStreamTask getTaskById(String taskId) {
        return tasks.get(taskId);
    }

    public List<String> getAllTaskIds() {
        return new ArrayList<>(tasks.keySet());
    }

    public void removeTask(String taskId) {
        pauseTask(taskId);
        statusMonitors.remove(taskId);
        tasks.remove(taskId);
        futures.remove(taskId);
    }

    public void pauseTask(String taskId) {
        Future<?> future = futures.get(taskId);
        if (future != null) {
            future.cancel(true);
        }
    }

    public void terminateThenSave(LiveConfig liveConfig,String taskId){
        LiveStreamTask task = tasks.get(taskId);
        task.terminate();
        removeTask(taskId);
        if (liveConfig.isConvertToMp4()) {
            String flvFilePath = liveConfig.getVideoPath() + liveConfig.getRoomId() + ".flv";
            String mp4FilePath = liveConfig.getVideoPath() + liveConfig.getRoomId() + ".mp4";
            VideoConverter.convertFlvToMp4(flvFilePath, mp4FilePath);
            System.out.println("start: flv-->mp4");

        }
    }

    private StatusMonitor getStatusMonitor(String taskId) {
        return statusMonitors.get(taskId);
    }

    public boolean isConnectionClosed(String taskId) {
        StatusMonitor statusMonitor = getStatusMonitor(taskId);
        return statusMonitor == null || statusMonitor.isConnectionClosed();
    }

    public double getDownloadSpeedAvg(String taskId) {
        StatusMonitor statusMonitor = getStatusMonitor(taskId);
        return statusMonitor == null ? 0.0 : statusMonitor.getDownloadSpeedAvg();
    }

    public double getDownloadSpeed(String taskId) {
        StatusMonitor statusMonitor = getStatusMonitor(taskId);
        return statusMonitor == null ? 0.0 : statusMonitor.getDownloadSpeed();
    }

    public long getDownloadedBytes(String taskId) {
        StatusMonitor statusMonitor = getStatusMonitor(taskId);
        return statusMonitor == null ? 0L : statusMonitor.getDownloadedBytes();
    }
}
