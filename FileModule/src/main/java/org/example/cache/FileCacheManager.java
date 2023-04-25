package org.example.cache;

import org.example.util.TimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import static java.lang.Thread.sleep;

/**
 * @author Genius
 * @date 2023/04/24 17:35
 **/

/**
 * 文件自动刷入管理类，不断监听文件是否需要自动写入
 */
public class FileCacheManager {

    private Logger logger = LoggerFactory.getLogger(FileCacheManager.class);
    private final List<FileCache> fileCaches;

    private AtomicLong sleepTime; //睡眠时间

    private ExecutorService watchPool;  //巡逻线程

    private ExecutorService autoSyncer; //生产者线程

    private volatile Watcher watcher;

    protected FileCacheManager(List<FileCache> fileCaches){
        this.fileCaches = new CopyOnWriteArrayList<>(fileCaches);
        initSleepTime();
        this.watchPool = Executors.newSingleThreadExecutor();
        this.autoSyncer = Executors.newFixedThreadPool(fileCaches.size());
    }

    /**
     * 根据文件缓存的刷盘时间得到一个最小的睡眠时间，减少空转
     */
    private void initSleepTime(){
        AtomicLong minSleepTime = new AtomicLong(Long.MAX_VALUE);
        fileCaches.forEach(item->{
            minSleepTime.set(Long.min(minSleepTime.get(), item.getSyncTime()));
        });
        this.sleepTime = minSleepTime;
    }

    public void start(){
        if(!fileCaches.isEmpty()){
            if(watcher==null){
                synchronized (FileCacheManager.class){
                    if(watcher==null){
                        watcher = new Watcher();
                        this.watchPool.submit(watcher);
                    }
                }
            }
        }
    }

    public boolean addFileCache(FileCache fileCache){
        if (this.fileCaches.indexOf(fileCache)==-1) {
            fileCaches.add(fileCache);
            initSleepTime();
        }
        return false;
    }

    public List<FileCache> getRunnableFileCaches(){
        return this.fileCaches;
    }

    class Watcher implements Runnable{

        @Override
        public void run() {
            for(;;){
                long now = TimeUtil.getCurrentSecond();
                for(FileCache cache:fileCaches){
                    BlockingQueue fileChannel = cache.getFileChannel();
                    if(fileChannel.isEmpty()){
                        if(cache.needAutoSync()){
                            logger.info("检测到需要强制刷新的文件 {}",cache.getFileName());
                            autoSyncer.submit(new AutoSyncer(cache));
                        }
                    }
                }
                now -= TimeUtil.getCurrentSecond();
                if(now<sleepTime.get()){
                    try {
                        Thread.sleep((sleepTime.get()-now)*1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }

    class AutoSyncer implements Runnable{

        FileCache fileCache;

        public AutoSyncer(FileCache fileCache){
            this.fileCache = fileCache;
        }
        @Override
        public void run() {
            BlockingQueue fileChannel = this.fileCache.getFileChannel();
            if(fileChannel.isEmpty()){
                if(fileCache.needAutoSync()) {
                    fileCache.forceSync();
                }
            }
        }
    }

}
