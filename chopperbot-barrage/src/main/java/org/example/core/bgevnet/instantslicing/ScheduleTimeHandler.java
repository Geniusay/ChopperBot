package org.example.core.bgevnet.instantslicing;

import com.alibaba.fastjson.JSONObject;
import org.example.bean.Live;
import org.example.constpool.BarrageModuleConstPool;
import org.example.constpool.FileNameBuilder;
import org.example.constpool.GlobalFileCache;
import org.example.constpool.PluginName;
import org.example.core.bgevnet.BarrageEvent;
import org.example.core.bgevnet.BarrageEventCenter;
import org.example.core.loadconfig.LoadConfig;
import org.example.core.taskcenter.task.ReptileTask;
import org.example.core.taskcenter.task.TaskStatus;
import org.example.init.InitPluginRegister;
import org.example.log.ChopperLogFactory;
import org.example.log.LoggerType;
import org.example.thread.NamedThreadFactory;
import org.example.util.BarrageUtil;
import org.example.util.TimeUtil;
import org.example.util.VideoUtil;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

import static org.example.constpool.ConstPool.NULL_TIME;

/**
 * @author Genius
 * @date 2023/09/17 21:15
 **/
public class ScheduleTimeHandler implements InstantSlicingHandler {

    private Map<String, ReptileTask> taskTimeMap;

    private Map<String, AtomicInteger> taskSplitTimes;

    private ScheduledExecutorService pool;

    private ScheduledFuture<?> scheduledFuture;

    private BarrageEventCenter center;

    private long splitTime;

    private String videoRootPath = "./config/LiveRecord/online";

    public ScheduleTimeHandler(JSONObject config) {
        this.center = InitPluginRegister.getPlugin(PluginName.BARRAGE_EVENT_PLUGIN, BarrageEventCenter.class);
        this.splitTime = config.getLong("duration");
        this.videoRootPath = (String) GlobalFileCache.ModuleSrcConfigFile.get("src","LiveRecord");
        this.pool =  Executors.newScheduledThreadPool(1, new NamedThreadFactory("quickSplit"));
        this.taskTimeMap = new ConcurrentHashMap<>();
        this.taskSplitTimes = new HashMap<>();
    }

    private void monitorTask(){
        long now = System.currentTimeMillis();
        for (Map.Entry<String, ReptileTask> entry : taskTimeMap.entrySet()) {
            ReptileTask task = entry.getValue();
            try {
                TaskStatus status = task.getType();
                if (!task.getStartTime().equals(NULL_TIME)) {
                    Long time = TimeUtil.getTimeNaos(entry.getValue().getStartTime());
                    Integer times = taskSplitTimes.get(task.getTaskId()).get();
                    if(now - time >= splitTime*(times+1) || status == TaskStatus.Finish){
                        taskSplitTimes.get(task.getTaskId()).incrementAndGet();
                        Object live = task.getRequest().getParam();
                        if (live instanceof Live) {
                            String platform = ((Live) live).getPlatform();
                            LoadConfig loadConfig = task.getLoadConfig();
                            String startTime = task.getLoadConfig().getStartTime();
                            String liver = ((Live) live).getLiver();
                            String barrageRoot = Path.of(BarrageModuleConstPool.BARRAGE_ROOT_PATH,"online",platform).toString();
                            String videoRoot = Path.of(videoRootPath,"online",platform).toString();
                            String oldBarrageFileName = FileNameBuilder.buildBarrageFileName(liver,startTime);
                            String newBarrageFileName = String.format(FileNameBuilder.buildBarrageFileName(liver, startTime + "_split(%s)"), times);
                            String oldVideoFileName = FileNameBuilder.buildVideoFileNameNoSuffix(liver,startTime)+loadConfig.getSuffix();
                            String newVideoFileName = String.format(FileNameBuilder.buildVideoFileNameNoSuffix(liver, startTime + "_split(%s)")+loadConfig.getSuffix(),times);
                            long startNaos = times*splitTime;
                            long endNaos = (times+1)*splitTime;
                            if (splitBarrageFile(Path.of(barrageRoot,oldBarrageFileName).toString(),
                                    Path.of(barrageRoot,newBarrageFileName).toString(),startNaos,endNaos)) {
                                if(spiltVideoFile(Path.of(videoRoot,oldVideoFileName).toString(),
                                        Path.of(videoRoot,newVideoFileName).toString(),startNaos/1000,endNaos/1000)){
                                    BarrageEvent event = new BarrageEvent.Builder()
                                                        .setAction("online")
                                                        .setPlatform(platform)
                                                        .setLiver(liver)
                                                        .setFilename(newBarrageFileName)
                                                        .setDate(startTime).build();
                                    event.setSuffix(task.getLoadConfig().getSuffix());
                                    center.event(event);
                                }
                            }
                        }
                        if(status==TaskStatus.Finish){
                            removeTask(task.getTaskId());
                        }

                    }
                }
            }catch (Exception e){
                ChopperLogFactory.getLogger(LoggerType.Barrage).error("Error:{}",e.toString());
            }
        }
    }


    private boolean splitBarrageFile(String oldPath,String newPath,long startTime,long endTime){
        return BarrageUtil.cutBarrageFile(oldPath,newPath,startTime,endTime);
    }

    private boolean spiltVideoFile(String oldPath,String newPath,long startSecond,long endSecond){
        return VideoUtil.cutVideoByFFMpeg(oldPath,newPath,startSecond,endSecond);
    }

    @Override
    public void handler() {
        this.scheduledFuture = pool.scheduleWithFixedDelay(this::monitorTask,0,1000, TimeUnit.MILLISECONDS);
    }

    @Override
    public void removeTask(String taskId) {
        taskTimeMap.remove(taskId);
        taskSplitTimes.remove(taskId);
    }

    @Override
    public void addTask(ReptileTask task) {
        if(!taskTimeMap.containsKey(task.getTaskId())){
            taskTimeMap.put(task.getTaskId(),task);
            taskSplitTimes.put(task.getTaskId(),new AtomicInteger(0));
        }
    }

    @Override
    public void shutdown() {
        scheduledFuture.cancel(true);
        pool.shutdown();
    }
}
