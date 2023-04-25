package org.example.cache;

import org.example.common.ConfigFile;
import org.example.constpool.GlobalFileCache;
import org.example.pojo.CommonConfigFile;
import org.example.pojo.Student;
import org.example.pojo.configfile.ModuleSrcConfigFile;
import org.example.exception.FileCacheException;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author Genius
 * @date 2023/04/24 03:30
 **/

public class FileCacheTest {
    static FileCache cache;

    static{

        try {
            cache = new FileCache<>(
                    new CommonConfigFile(
                            "E:\\Project\\ChopperBot\\FileModule\\src\\main\\resources\\",
                            "test.json",
                            new ConcurrentLinkedQueue<Student>()
                    )
            );
            FileCacheManagerInstance.getInstance().addFileCache(cache);
        } catch (FileCacheException e) {
            throw new RuntimeException(e);
        }
    }

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
        FileCacheManagerInstance.getInstance().start();
        //追加数组的某个元素
        for(int i=0;i<10;i++){
            GlobalFileCache.ModuleSrcConfigFile.append(i,"sectionwork","src","0");
        }
        //追加数组
        for(int i=0;i<10;i++){
            GlobalFileCache.ModuleSrcConfigFile.append(i,"sectionwork","src","-1");
        }
        //追加类中的元素
        for(int i=0;i<10;i++){
            GlobalFileCache.ModuleSrcConfigFile.append(i,"barrage","src");
        }
        Thread.sleep(500000);
    }

    @Test
    public void TestWrite() throws FileCacheException,InterruptedException{
        FileCacheManagerInstance.getInstance().start();
        //更改数组元素
        for(int i=0;i<10;i++){
            GlobalFileCache.ModuleSrcConfigFile.writeKeys(i,"sectionwork","src","0");
        }
        //追加数组
        for(int i=0;i<10;i++){
            GlobalFileCache.ModuleSrcConfigFile.writeKeys(i,"sectionwork","src","-1");
        }
        //更改类中的元素
        for(int i=0;i<10;i++){
            GlobalFileCache.ModuleSrcConfigFile.writeKeys(i,"barrage","src");
        }
        Thread.sleep(500000);
    }

    @Test
    public void TestArrayAdd() throws InterruptedException, FileCacheException {
        ModuleSrcConfigFile moduleSrcConfigFile = new ModuleSrcConfigFile();
        FileCache fileCache = new FileCache(moduleSrcConfigFile);

        FileCacheManager manager = new FileCacheManager(List.of(fileCache));
        manager.start();

        System.out.println(manager.addFileCache(fileCache));
    }

    @Test
    public void TestAddCache() throws FileCacheException, InterruptedException {
        FileCacheManagerInstance.getInstance().start();
        for(int i=0;i<10;i++){
            Student student = new Student(Integer.toString(i),i,"jsu"+i,"major"+i,null,null);
            cache.writeKeys(student,"-1");
        }
        Thread.sleep(500000);
    }

}
