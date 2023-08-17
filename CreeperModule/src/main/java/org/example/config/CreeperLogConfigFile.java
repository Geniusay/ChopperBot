package org.example.config;

import org.example.bean.ConfigFile;
import org.example.bean.FileType;
import org.example.constpool.CreeperModuleConstPool;
import org.example.core.taskcenter.task.ReptileTask;
import org.example.core.taskcenter.task.TaskRecord;
import org.example.util.TimeUtil;

import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

/**
 * @author Genius
 * @date 2023/07/29 00:03
 **/
public class CreeperLogConfigFile extends ConfigFile<Map<String,List<TaskRecord>>> {
    private static final String filePath = CreeperModuleConstPool.CREEPER_ROOT +"/log";

    public CreeperLogConfigFile(List<TaskRecord> data) {
        super(filePath,"creeper-"+ TimeUtil.getToday_YMD()+".log" ,
                Map.of("task",data)
        , FileType.LOG);
    }

    //检查是不是需要更新日志
    public static boolean needNewDayLog(String fileName){
        return fileName.equals("creeper-"+ TimeUtil.getToday_YMD()+".log");
    }

    public static String getFullFilePath(){
        return Paths.get(filePath,"creeper-"+ TimeUtil.getToday_YMD()+".log").toString();
    }
}
