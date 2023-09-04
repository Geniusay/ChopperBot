package org.example.log;

import lombok.extern.java.Log;
import org.example.constpool.ConstPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * @author Genius
 * @date 2023/07/28 23:22
 **/
public class ChopperLogFactory {

    public static Map<String,LoggerType> nameToType = Map.of(
            ConstPool.HOT,LoggerType.Hot,
            ConstPool.CREEPER,LoggerType.Creeper,
            ConstPool.FILE, LoggerType.File
    );

    public static Logger getLogger(LoggerType loggerType){
        return getLogger(loggerType.getLoggerName());
    }

    public static Logger getLogger(String name){
        return LoggerFactory.getLogger(name);
    }
}
