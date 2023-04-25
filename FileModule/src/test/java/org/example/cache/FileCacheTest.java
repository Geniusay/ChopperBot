package org.example.cache;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPObject;
import org.example.bean.ModuleSrcConfigFile;
import org.example.bean.Student;
import org.example.cache.FileCache;
import org.example.exception.FileCacheException;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static org.apache.logging.log4j.message.MapMessage.MapFormat.JSON;

/**
 * @author Genius
 * @date 2023/04/24 03:30
 **/

public class FileCacheTest {

    @Test
    public void TestFileCache() throws FileCacheException, InterruptedException {
        ModuleSrcConfigFile moduleSrcConfigFile = new ModuleSrcConfigFile();
        FileCache fileCache = new FileCache(moduleSrcConfigFile);

        FileCacheManager manager = new FileCacheManager(List.of(fileCache));
        manager.start();
        Thread.sleep(500000);
    }

    @Test
    public void TestFileCacheManager() throws FileCacheException, InterruptedException {
        ModuleSrcConfigFile moduleSrcConfigFile = new ModuleSrcConfigFile();
        FileCache fileCache = new FileCache(moduleSrcConfigFile);
        FileCacheManager manager = new FileCacheManager(List.of(fileCache));
        manager.start();
        Thread.sleep(500000);
    }

    @Test
    public void TestGet() throws FileCacheException, InterruptedException {
        ModuleSrcConfigFile moduleSrcConfigFile = new ModuleSrcConfigFile();
        FileCache fileCache = new FileCache(moduleSrcConfigFile);

        FileCacheManager manager = new FileCacheManager(List.of(fileCache));
        manager.start();

        System.out.println(fileCache.get("barrage","src"));
        fileCache.append(1,"barrage","src");
    }

    @Test
    public void TestAppend() throws FileCacheException, InterruptedException {
        ModuleSrcConfigFile moduleSrcConfigFile = new ModuleSrcConfigFile();
        FileCache fileCache = new FileCache(moduleSrcConfigFile);

        FileCacheManager manager = new FileCacheManager(List.of(fileCache));
        manager.start();
        //追加数组的某个元素
        for(int i=0;i<10;i++){
            fileCache.append(i,"sectionwork","src","0");
        }
        //追加数组
        for(int i=0;i<10;i++){
            fileCache.append(i,"sectionwork","src","-1");
        }
        //追加类中的元素
        for(int i=0;i<10;i++){
            fileCache.append(i,"barrage","src");
        }
        Thread.sleep(500000);
    }

    @Test
    public void TestWrite() throws FileCacheException,InterruptedException{
        ModuleSrcConfigFile moduleSrcConfigFile = new ModuleSrcConfigFile();
        FileCache fileCache = new FileCache(moduleSrcConfigFile);

        FileCacheManager manager = new FileCacheManager(List.of(fileCache));
        manager.start();
        //更改数组元素
        for(int i=0;i<10;i++){
            fileCache.writeKeys(i,"sectionwork","src","0");
        }
        //追加数组
        for(int i=0;i<10;i++){
            fileCache.writeKeys(i,"sectionwork","src","-1");
        }
        //更改类中的元素
        for(int i=0;i<10;i++){
            fileCache.writeKeys(i,"barrage","src");
        }
        Thread.sleep(500000);
    }

    @Test
    public void TestJson(){
        Student student = new Student("Genius",18,"qinghua","SC",List.of("1","2","3"),null);
        String s = JSONObject.toJSONString("");
        JSONObject jsonObject = JSONObject.parseObject(s);
        jsonObject.put("hobby",List.of("1","2","3","4").toString());
        System.out.println(jsonObject);
    }

}
