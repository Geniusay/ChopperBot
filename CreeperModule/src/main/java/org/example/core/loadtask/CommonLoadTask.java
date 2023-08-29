package org.example.core.loadtask;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.util.TypeUtils;
import org.example.core.loadconfig.LoadConfig;
import org.example.core.taskcenter.task.TaskRecord;

/**
 * @author Genius
 * @date 2023/08/21 01:02
 **/
public abstract class CommonLoadTask<T> implements LoadTask<T>{

    protected LoadConfig loadConfig;

    public CommonLoadTask(LoadConfig loadConfig) {
        this.loadConfig = loadConfig;
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

    @Override
    public void restore() {

    }

    public static void main(String[] args) {
        TypeUtils.isProxy(TaskRecord.class);
    }
}
