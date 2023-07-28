package org.example.bean;

/**
 * @author Genius
 * @date 2023/04/26 01:48
 **/
public enum FileType {
    CHOPPER_BOT("ChopperBot"),
    BARRAGE("弹幕设置"),
    HOT("热门设置"),
    CREEPER("爬虫设置"),
    VIDEO("文件下载"),
    COMMON("普通文件"),

    LOG("日志文件");
    private final String name;
    FileType(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }
}
