package org.example.core.manager;

import org.example.core.control.LoadTask;
import org.example.core.factory.TaskFactory;
import org.example.pojo.download.LoadConfig;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * 任务管理器
 * @author 燧枫
 * @date 2023/4/25 17:22
 */
public class LoadTaskManager {

    private final ConcurrentHashMap<String, LoadTask> taskMap;
    private final TaskFactory taskFactory;

    public LoadTaskManager() {
        this.taskMap = new ConcurrentHashMap<>();
        // 默认任务工场
        this.taskFactory = new TaskFactory();
    }

    public LoadTaskManager(TaskFactory customTaskFactory) {
        this.taskMap = new ConcurrentHashMap<>();
        // 自定义任务工场
        this.taskFactory = customTaskFactory;
    }

    // 创建一个任务并返回唯一的 key
    public String addTask(LoadConfig loadConfig) {
        // 根据主播名和时间戳生成唯一的 key
        String key = generateKey(loadConfig);

        // 创建任务并将其添加到任务映射中
        LoadTask loadTask = taskFactory.getLoadTask(loadConfig);
        taskMap.put(key, loadTask);

        // 返回生成的 key
        return key;
    }

    // 生成唯一的key
    private String generateKey(LoadConfig loadConfig) {
        // 以主播名和当前时间戳作为 key 的基础
        String baseKey = loadConfig.getAnchorName() + "_" + loadConfig.getStartTime();

        // 确保 key 是唯一的，如果已存在，则添加一个数字后缀
        String uniqueKey = baseKey;
        int suffix = 1;
        while (taskMap.containsKey(uniqueKey)) {
            uniqueKey = baseKey + "_" + suffix;
            suffix++;
        }

        return uniqueKey;
    }

    // 获取一个任务对象
    public LoadTask getTask(String key) {
        return taskMap.get(key);
    }

    // 开启一个任务
    public void startTask(String key) {
        LoadTask loadTask = taskMap.get(key);
        if (loadTask != null) {
            loadTask.start();
        }
    }

    // 结束一个任务
    public void endTask(String key) {
        LoadTask loadTask = taskMap.get(key);
        if (loadTask != null) {
            loadTask.end();
        }
    }

    // 获取任务运行状态
    public boolean isTaskRunning(String key) {
        LoadTask loadTask = taskMap.get(key);
        if (loadTask != null) {
            return loadTask.isRunning();
        }
        return false;
    }

    // 获取任务缓存里的弹幕条数
    public int getCacheSize(String key) {
        LoadTask loadTask = taskMap.get(key);
        if (loadTask != null) {
            return loadTask.getCacheSize();
        }
        return 0;
    }

    // 将任务缓存保存至别处并清空缓存
    public int flushTaskCacheAndSave(String key) {
        LoadTask loadTask = taskMap.get(key);
        if (loadTask != null) {
            return loadTask.flushCacheAndSave(key);
        }
        return 0;
    }

    // 获取所有已经结束的任务的key
    public Set<String> getFinishedTaskKeys() {
        return taskMap.entrySet().stream()
                .filter(entry -> !entry.getValue().isRunning())
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());
    }

    // 获取所有的key
    public Set<String> getAllTaskKeys() {
        return Collections.unmodifiableSet(taskMap.keySet());
    }

    // 清除出任务列表
    public void taskFinished(String key) {
        taskMap.remove(key);
    }
}
