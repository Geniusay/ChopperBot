package org.example.core.creeper.loadtask;

import org.example.core.loadtask.WebMagicLoadTask;
import org.example.log.ChopperLogFactory;
import org.example.log.LoggerType;
import org.example.core.loadconfig.LoadConfig;
import org.example.util.ExceptionUtil;

/**
 * @author Genius
 * @date 2023/07/21 10:22
 **/

/**
 * 热门模块的抽象类，它包含了任务完成状态以及失败成功日志，以及后续数据的获取和返回
 * @param <T>
 */
public abstract class HotModuleLoadTask<T> extends WebMagicLoadTask<T> {
    public enum FinishFlag{
        FINISH,NOT_FINISH,FAIL
    }
    private FinishFlag finishFlag = FinishFlag.NOT_FINISH;

    public HotModuleLoadTask(LoadConfig loadConfig) {
        super(loadConfig);
    }


    protected void fail(Exception e){
        finishFlag = FinishFlag.FAIL;
        ChopperLogFactory.getLogger(LoggerType.Hot).error("loadTask{} finish fail Error:{}",this.getClass().getName(), ExceptionUtil.getCause(e));
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
