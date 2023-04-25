package org.example.common;

/**
 * @author Genius
 * @date 2023/04/26 01:48
 **/
public enum FileType {
    CHOPPER_BOT("ChopperBot"),
    BARRAGE("弹幕设置"),
    CREEPER("爬虫设置"),
    VIDEO("文件下载"),
    COMMON("普通文件");
    private final String name;
    FileType(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }
}
