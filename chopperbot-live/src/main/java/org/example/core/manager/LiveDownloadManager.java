package org.example.core.manager;

import org.example.core.component.LiveStreamTask;
import org.example.core.component.StatusMonitor;
import org.example.core.creeper.loadconfig.LoadLiveConfig;
import org.example.core.factory.LiveTaskFactory;
import org.example.plugin.CommonPlugin;
import org.example.thread.NamedThreadFactory;
import org.example.utils.VideoConverter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 下载任务管理器
 * @author 燧枫
 * @date 2023/5/19 17:12
 */
public class LiveDownloadManager extends CommonPlugin {

    private ExecutorService executor;
    private ExecutorService logExecutor;
    private Map<String, Future<?>> futures;
    private Map<String, LiveStreamTask> tasks;
    private LiveTaskFactory taskFactory;
    private Map<String, StatusMonitor> statusMonitors;

    public LiveDownloadManager(String module, String pluginName, List<String> needPlugins, boolean isAutoStart) {
        super(module, pluginName, needPlugins, isAutoStart);
    }

    public LiveDownloadManager(int maxTasks) {
        super(null,null,null,true);
        this.executor = Executors.newFixedThreadPool(maxTasks);
        this.logExecutor = Executors.newFixedThreadPool(maxTasks);
        this.futures = new HashMap<>();
        this.tasks = new HashMap<>();
        this.taskFactory = new LiveTaskFactory();
        this.statusMonitors = new HashMap<>();
    }

    @Override
    public boolean init() {
        NamedThreadFactory poolName = new NamedThreadFactory("LiveManager");
        this.executor = Executors.newCachedThreadPool(poolName);
        this.logExecutor = Executors.newCachedThreadPool(poolName);
        this.futures = new HashMap<>();
        this.tasks = new HashMap<>();
        this.taskFactory = new LiveTaskFactory();
        this.statusMonitors = new HashMap<>();
        return true;
    }



    public String addTask(LoadLiveConfig liveConfig) throws FileNotFoundException {
        LiveStreamTask task = this.taskFactory.create(liveConfig);
        if (task == null) {
            throw new IllegalArgumentException("Unable to create task for live config: " + liveConfig);
        }

        StatusMonitor statusMonitor = new StatusMonitor();
        String taskId = System.currentTimeMillis() + liveConfig.getRoomId();  // 创建唯一的任务标识符
        statusMonitors.put(taskId, statusMonitor);
        tasks.put(taskId, task);

        OutputStream fileIO = new FileOutputStream(Path.of(liveConfig.getVideoPath(),liveConfig.getVideoName() + ".flv").toString());

        Future<?> future = executor.submit(() -> {
            task.start(statusMonitor, fileIO);
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

    public Object waitResult(String taskId,LoadLiveConfig liveConfig) throws ExecutionException, InterruptedException {
        Future<?> future = futures.get(taskId);
        if(future!=null){
            future.get();
            return terminateThenSave(liveConfig,taskId);
        }
        return null;
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

    public String terminateThenSave(LoadLiveConfig liveConfig,String taskId){
        LiveStreamTask task = tasks.get(taskId);
        task.terminate();
        removeTask(taskId);
        String path = Path.of(liveConfig.getVideoPath(),liveConfig.getRoomId() + ".flv").toString();
        if (liveConfig.isConvertToMp4()) {
            String mp4FilePath = Path.of(liveConfig.getVideoPath(),liveConfig.getRoomId() + ".mp4").toString();
            VideoConverter.convertFlvToMp4(path, mp4FilePath);
            System.out.println("start: flv-->mp4");
            path = mp4FilePath;
        }
        return path;
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

    public void showDownloadTable(String taskId){
        StatusMonitor statusMonitor = getStatusMonitor(taskId);
        logExecutor.submit(()->{
            statusMonitor.downloadLogTable(taskId);
        });
    }

    @Override
    public void shutdown() {
        logExecutor.shutdown();
        executor.shutdown();
        super.shutdown();
    }
}
