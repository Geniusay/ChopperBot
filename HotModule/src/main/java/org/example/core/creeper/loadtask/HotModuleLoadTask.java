package org.example.core.creeper.loadtask;

import org.example.core.loadtask.CommonLoadTask;
import org.example.log.ChopperLogFactory;
import org.example.log.LoggerType;
import org.example.pojo.download.LoadConfig;

/**
 * @author Genius
 * @date 2023/07/21 10:22
 **/

/**
 * 热门模块的抽象类，它包含了任务完成状态以及失败成功日志，以及后续数据的获取和返回
 * @param <T>
 */
public abstract class HotModuleLoadTask<T> extends CommonLoadTask<T> {

    public HotModuleLoadTask(LoadConfig loadConfig) {
        super(loadConfig);
    }

    public enum FinishFlag{
        FINISH,NOT_FINISH,FAIL
    }
    private FinishFlag finishFlag = FinishFlag.NOT_FINISH;


    public T start() {
        clearFinishFlag();
        return this.start0();
    }

    protected abstract T start0();
    protected void fail(Exception e){
        finishFlag = FinishFlag.FAIL;
        ChopperLogFactory.getLogger(LoggerType.Hot).error("loadTask{} finish fail Error:{}",this.getClass().getName(),e.getMessage());
    }

    protected void success(){
        finishFlag = FinishFlag.FINISH;
    }



    public FinishFlag isFinish(){
        return finishFlag;
    }

    public void clearFinishFlag(){finishFlag = FinishFlag.NOT_FINISH;}

    @Override
    public void end() {

    }

    @Override
    public boolean isRunning() {
        return false;
    }

    @Override
    public int getCacheSize() {
        return 0;
    }

    @Override
    public int flushCacheAndSave(String key) {
        return 0;
    }
}
