package org.example.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Genius
 * @date 2023/07/28 23:22
 **/
public class ChopperLogFactory {

    public static Logger getLogger(LoggerType loggerType){
        return LoggerFactory.getLogger(loggerType.getLoggerName());
    }
}
