package org.example.api;

import org.example.cache.FileCacheManager;
import org.example.constpool.ConstPool;
import org.example.constpool.GlobalFileCache;
import org.example.constpool.PluginName;
import org.example.init.InitPluginRegister;
import org.example.plugin.CommonPlugin;
import org.junit.Test;

import java.io.File;
import java.security.Key;
import java.util.*;

/**
 * @Description
 * @Author welsir
 * @Date 2023/9/9 12:36
 */
public class BarrageApi {

    private static final String BARRAGE_FILE_ROOT_PATH = (String) GlobalFileCache.ModuleSrcConfigFile.get("src", ConstPool.BARRAGE);

    private static final HashMap<String,List<File>> barrageFileMap = new HashMap<>();

    private static final String[] platForms = {"bilibili","douyu","douyin","huya","tiktok","twitch"};
    public HashMap getAllBarrageFile(){
        for (String plathForm : platForms) {
            File platformDir  = new File(BARRAGE_FILE_ROOT_PATH + "/" + plathForm);
            for (File anchorDir : Objects.requireNonNull(platformDir.listFiles())) {
                if(anchorDir.isDirectory()){
                    String anchor = anchorDir.getName();
                    List<File> fileList = new ArrayList<>(Arrays.asList(Objects.requireNonNull(anchorDir.listFiles())));
                    barrageFileMap.put(anchor, fileList);
                }
            }
        }
        return barrageFileMap;
    }
}
