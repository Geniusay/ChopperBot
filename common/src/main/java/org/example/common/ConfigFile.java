package org.example.common;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

/**
 * @author Genius
 * @date 2023/04/21 02:24
 **/

//配置文件的抽象类
public abstract class ConfigFile<T> {

    private String filePath;
    private String fileName;

    private T data;

    /**
    * 用于打包配置文件
     */
    public Map<String, Object> packageConfig() {


        return Map.of(
                "data",data,
                "updateTime", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
        );
    }

    public ConfigFile() {
    }

    public ConfigFile(String filePath, String fileName, T data) {
        this.filePath = filePath;
        this.fileName = fileName;
        this.data = data;
    }

    public String getFilePath() {
        return filePath;
    }

    public String getFileName() {
        return fileName;
    }

    protected T getData() {
        return data;
    }
}
