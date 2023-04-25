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
        for(int i=0;i<1;i++){
            fileCache.append("sectionwork",Integer.toString(i));
        }
        Thread.sleep(500000);
    }

    @Test
    public void TestGet() throws FileCacheException, InterruptedException {
        ModuleSrcConfigFile moduleSrcConfigFile = new ModuleSrcConfigFile();
        FileCache fileCache = new FileCache(moduleSrcConfigFile);

        FileCacheManager manager = new FileCacheManager(List.of(fileCache));
        manager.start();

        System.out.println(fileCache.get("sectionwork", "src"));
        //fileCache.append(1,"sectionwork","src","0");
    }

    @Test
    public void TestJson(){
        String a = "{username:'[1,2,3,4]',age:{'hello':'world'}}";
        Object jsonObject = JSONObject.parse(a);
        System.out.println(jsonObject);
        System.out.println(jsonObject.getClass());

        jsonObject = JSONArray.parse( ((JSONObject) jsonObject).get("username").toString() );
        System.out.println(jsonObject);
        System.out.println(jsonObject.getClass());
    }

}
