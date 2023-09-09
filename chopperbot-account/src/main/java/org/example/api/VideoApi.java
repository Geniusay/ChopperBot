package org.example.api;

import org.example.constpool.ConstPool;
import org.example.constpool.GlobalFileCache;

import java.io.File;
import java.util.*;

/**
 * @Description
 * @Author welsir
 * @Date 2023/9/9 12:36
 */
public class VideoApi {

    private static final String VIDEO_FILE_ROOT_PATH = (String) GlobalFileCache.ModuleSrcConfigFile.get("src", ConstPool.LIVE_RECORD);

    private static final HashMap<String, Map<String,List<File>>> liveRecordMap = new HashMap<>();

    public HashMap getAllLiveRecord(){
        for (File type : Objects.requireNonNull(new File(VIDEO_FILE_ROOT_PATH).listFiles(File::isDirectory))) {
            Map<String, List<File>> recordMap = new HashMap<>();

            for (File platform : Objects.requireNonNull(type.listFiles(File::isDirectory))) {
                List<File> videoFiles = Arrays.asList(Objects.requireNonNull(platform.listFiles()));

                recordMap.put(platform.getName(), videoFiles);
            }
            liveRecordMap.put("online".equals(type.getName()) ? "online" : "record", recordMap);
        }
        return liveRecordMap;
    }

}
