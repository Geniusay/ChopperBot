package org.example.log;

/**
 * @author Genius
 * @date 2023/07/28 23:22
 **/
public enum LoggerType {
    System("ChopperBot🤖"),
    Creeper("Creeper    "),
    File("FileModule "),
    Hot("HotModule  "),
    Barrage("Barrage  ");
    private String loggerName;

    LoggerType(String name){
        this.loggerName = name;
    }

    public String getLoggerName(){
        return loggerName;
    }
}
