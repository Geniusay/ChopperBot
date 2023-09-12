package org.example.core.loadtask;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.util.TypeUtils;
import org.example.core.loadconfig.LoadConfig;
import org.example.core.taskcenter.task.TaskRecord;
import org.example.log.ChopperLogFactory;
import org.example.log.LoggerType;
import org.slf4j.Logger;

/**
 * @author Genius
 * @date 2023/08/21 01:02
 **/
public abstract class CommonLoadTask<T> implements LoadTask<T>{

    protected LoadConfig loadConfig;

    protected Logger logger;
    public CommonLoadTask(LoadConfig loadConfig) {
        this.loadConfig = loadConfig;
        this.logger = ChopperLogFactory.getLogger(LoggerType.Creeper);
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

}
