package org.example.bean;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

/**
 * @author Genius
 * @date 2023/04/21 02:24
 **/

//配置文件的抽象类，只负责构建配置文件最基础的架构，一般不用来存放配置文件本身的内容
public abstract class ConfigFile<T>{

    private FileType fileType;
    private String filePath;
    private String fileName;

    private T data;     //json文件的结构不是文件的数据 例如 {username:"",password:""}

    //上一次更新时间
    private LocalDateTime updateTime;

    /**
    * 用于最开始创建配置文件结构的打包
     * @return Map
     */
    public Map<String, Object> packageConfig() {
       return this.packageConfig(this.data);
    }

    /**
     * 用于给外部函数提供的内容打包
     * @return
     */
    public Map<String,Object> packageConfig(T data){
       updateConfigTime();
        return Map.of(
                "data",data,
                "updateTime", updateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
        );
    }

    /**
     * 只进行时间更新操作
     * @param map
     * @return
     */
    public Map<String,Object> onlyUpdateTime(Map<String,Object> map){
        updateConfigTime();
        if (map.containsKey("updateTime")) {
            map.put("updateTime",updateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        }
        return map;
    }

    /**
     * 更新配置文件类本身的时间
     */
    public void updateConfigTime(){
        updateTime = LocalDateTime.now();
    }


    public ConfigFile() {
    }

    public ConfigFile(String filePath, String fileName, T data) {
        this.filePath = filePath;
        this.fileName = fileName;
        this.data = data;
        this.updateTime = LocalDateTime.now();
        this.fileType = FileType.COMMON;
    }

    public ConfigFile(String filePath,String fileName,T data,FileType fileType){
        this.filePath = filePath;
        this.fileName = fileName;
        this.data = data;
        this.updateTime = LocalDateTime.now();
        this.fileType = fileType;
    }

    public String getFilePath() {
        return this.filePath;
    }

    public String getFileName() {
        return this.fileName;
    }

    public LocalDateTime getUpdateTime() {return this.updateTime;}

    public FileType getFileType(){
        return this.fileType;
    }

    //不推荐直接使用
    public T getData() {
        return this.data;
    }

    public void setData(T data){this.data = data;}

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setFilePath(String filePath){
        this.filePath = filePath;
    }



}
