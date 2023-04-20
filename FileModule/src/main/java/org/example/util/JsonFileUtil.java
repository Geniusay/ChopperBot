package org.example.util;

import com.alibaba.fastjson.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * @author Genius
 * @date 2023/04/20 10:53
 **/

/**
 * 操作json文件
 */
public class JsonFileUtil {

    private static final Logger logger = LoggerFactory.getLogger(JsonFileUtil.class);

    /* ------------------read json file------------------ */

    /**
     * 读取json文件
     * @param filePath 文件路径，应该为全路径
     * @param fileName 文件名
     * @return Map<String, Object> 返回Map<String, Object>类型的数据
     */
    public static Map<String, Object> readJsonFile(String filePath, String fileName) {
        return readJsonFile(Paths.get(filePath, fileName).toString());
    }

    /**
     * 读取json文件
     * @param fullPath 文件路径,包含文件名
     * @return Map<String, Object> 返回Map<String, Object>类型的数据
     */
    public static Map<String,Object> readJsonFile(String fullPath){
        Map<String, Object> maps = null;
        JSONObject jsonObject = readJsonFileToJSONObject(fullPath);
        if (Objects.nonNull(jsonObject)) {
            maps = jsonObject.getInnerMap();
        }
        return maps;
    }

    /**
     * 读取json文件转为JSONObject
     * @param fullPath 文件路径,包含文件名
     * @return JSONObject
     */
    public static JSONObject readJsonFileToJSONObject(String fullPath){
        return readJsonFileToObject(fullPath, JSONObject.class);
    }

    /** 读取json文件转为对应的类
     * @param fullPath 文件路径,包含文件名
     * @param clazz 类
     * @return T
     */
    public static <T> T readJsonFileToObject(String fullPath, Class<T> clazz){
        T t = null;
        Path dir = Paths.get(fullPath);
        try{
            if (FileUtil.isFileExist(dir.toString())) {
                String res = Files.readString(dir, StandardCharsets.UTF_8);
                logger.info("读取json文件成功, 文件内容为: {}", res);

                t = JSON.parseObject(res, clazz);
            }
        }catch (Exception e){
            logger.error("读取json文件失败", e);
        }
        return t;
    }

    /* ------------------write json file------------------ */

    /**
    * 写入json文件
    * @param filePath 文件路径，应该为全路径
    * @param fileName 文件名
    * @param data 写入Json数据
    * @return File
     */
    public static File writeJsonFile(String filePath, String fileName, Map<String, Object> data) {
        return writeJsonFile(Paths.get(filePath, fileName).toString(), data);
    }

    /**
     * 读取json文件
     * @param fullPath 文件路径，应该为全路径
     * @param data 写入Json数据
     * @return File
     */
    public static File writeJsonFile(String fullPath, Map<String, Object> data) {
       return writeJsonFile0(fullPath, JSON.toJSONString(data,true),true);
    }

    /**
     * 写入json文件,如果文件不存在则报错
     * @param filePath 文件路径，应该为全路径
     * @param fileName 文件名
     * @param data 写入Json数据
     * @return File
     */
    public static <T> File writeJsonFileIsExist(String filePath, String fileName, T data) {
        return writeJsonFileIsExist(Paths.get(filePath, fileName).toString(), data);
    }

    /**
     * 写入json文件,如果文件不存在则报错
     * @param fullPath 文件路径，应该为全路径
     * @param data 写入Json数据
     * @return File
     */
    public static <T> File writeJsonFileIsExist(String fullPath, T data) {
        return writeJsonFile0(fullPath, JSON.toJSONString(data,true),false);
    }

    /**
     * 将Obj写入json文件
     * @param filePath 文件路径，应该为全路径
     * @param fileName 文件名
     * @param obj 写入Json数据
     * @return File
     */
    public static <T> File writeJsonFile(String filePath, String fileName, T obj) {
        return writeJsonFile(Paths.get(filePath, fileName).toString(), obj);
    }

    /**
     * 将Obj写入json文件
     * @param fullPath 文件路径，应该为全路径
     * @param obj 写入Json数据
     * @return File
     */
    public static <T> File writeJsonFile(String fullPath, T obj) {
        return writeJsonFile0(fullPath, JSON.toJSONString(obj,true),true);
    }

    /**
     * 写入json文件，如果文件不存在则自动创建
     * @param fullPath 文件路径，应该为全路径
     * @param json  写入Json数据
     * @param autoCreate  是否自动创建文件
     * @return  File
     */
    public static File writeJsonFile0(String fullPath, String json,boolean autoCreate) {
        Path dir = Paths.get(fullPath);
        try{
            if(!FileUtil.isFileExist(dir.toString())&&autoCreate){
                Files.createFile(dir);
                logger.info("新建文件{}",dir);
            }
            if (FileUtil.isFileExist(dir.toString())) {
                Files.writeString(dir, json, StandardCharsets.UTF_8);
                logger.info("写入json文件成功, 文件内容为: {}", json);
            }else {
                logger.error("写入json文件失败, 文件不存在");
                return null;
            }
        }catch (Exception e){
            logger.error("写入json文件失败", e);
            return null;
        }
        return dir.toFile();
    }

    /**
     * 写入大j对象到Json文件中
     * @param fullPath  文件路径，应该为全路径
     * @param data 写入Json数据
     * @return File
     */
    public static File writeBigJsonFile(String fullPath, Map<String, Object> data) {
        Path dir = Paths.get(fullPath);
        try{
            if(!FileUtil.isFileExist(dir.toString())){
                Files.createFile(dir);
                logger.info("新建文件{}",dir);
            }
            if (FileUtil.isFileExist(dir.toString())) {
                JSONWriter writer = new JSONWriter(Files.newBufferedWriter(dir, StandardCharsets.UTF_8));
                writer.startObject();
                for (Map.Entry<String, Object> stringObjectEntry : data.entrySet()) {
                    String key = stringObjectEntry.getKey();
                    Object value = stringObjectEntry.getValue();
                    writer.writeKey(key);
                    writer.writeValue(value);
                    logger.info("写入json类成功, 类内容为: {}:{}", key, value);
                }
                writer.endObject();
                writer.close();

            }else {
                logger.error("写入json文件失败, 文件不存在");
                return null;
            }
        }catch (Exception e){
            logger.error("写入json文件失败", e);
            return null;
        }
        return dir.toFile();
    }

    /**
     * 写入大j对象到Json文件中
     * @param fullPath  文件路径，应该为全路径
     * @param Objs 写入大对象的数组
     * @return File
     */
    public static <T> File writeBigJsonFile(String fullPath, List<T> Objs) {
        Path dir = Paths.get(fullPath);
        try{
            if(!FileUtil.isFileExist(dir.toString())){
                Files.createFile(dir);
                logger.info("新建文件{}",dir);
            }
            if (FileUtil.isFileExist(dir.toString())) {
                JSONWriter writer = new JSONWriter(Files.newBufferedWriter(dir, StandardCharsets.UTF_8));
                writer.startArray();
                for (T obj : Objs) {
                    writer.writeValue(obj);
                    logger.info("写入json类成功, 类内容为: {}", obj);
                }
                writer.endArray();
                writer.close();

            }else {
                logger.error("写入json文件失败, 文件不存在");
                return null;
            }
        }catch (Exception e){
            logger.error("写入json文件失败", e);
            return null;
        }
        return dir.toFile();
    }
}
