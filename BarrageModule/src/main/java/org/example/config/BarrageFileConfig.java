package org.example.config;
/**
 * @description : [用于提取config模块的弹幕文件]
 * @author : [Welsir]
 * @createTime : [2023/5/15 22:12]
 */

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.example.pojo.Barrage;
import org.example.util.JsonFileUtil;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.*;

/**
 * @author welsir
 * @descrption: use to get the config module's file.
 * @date 2023/5/15 22:12
 */
public class BarrageFileConfig {

    private static final String barrageFile = "config/barrage/";

    private static HashMap<String, List<Barrage>> anchorMap = new HashMap<>();

    public static HashMap getBarrageFile() {
        File rootFile = new File(barrageFile);
        if (rootFile.exists() && rootFile.isDirectory()) {
            classifyAndProcessFolders(rootFile);
        }
        return anchorMap;
    }

    public static void setMap(Map map) {
        anchorMap = (HashMap<String, List<Barrage>>)map;
    }

    private static void classifyAndProcessFolders(File folder) {
        File[] subFolders = folder.listFiles(File::isDirectory);
        if (subFolders != null) {
            for (File subFolder : subFolders) {
                String folderName = subFolder.getName();
                File[] jsonFiles = subFolder.listFiles((dir, name) -> name.toLowerCase().endsWith(".json"));
                if (jsonFiles != null) {
                    for (File jsonFile : jsonFiles) {
                        // 处理JSON文件，例如读取内容或进行其他操作
                        JSONObject objects;
                        JSONArray data = null;
                        try {
                            String jsonStr = Files.readString(jsonFile.toPath());
                            objects = JSON.parseObject(jsonStr);
                            data = objects.getJSONArray("data");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        List<Barrage> barrageList = Barrage.copyProperty(data);
                        anchorMap.put(folderName+"-"+jsonFile.getName(), barrageList);
                    }
                }
                // 递归遍历子文件夹
                classifyAndProcessFolders(subFolder);
            }
        }
    }

    public static void main(String[] args) {

        //getBarrageFile();
//        String encode = URLEncoder.encode("config\\barrage\\大司马\\douyu_大司马_05-07-14_58_17.json", StandardCharsets.UTF_8);
//        Map<String, Object> map = JsonFileUtil.readJsonFile(encode);
//        System.out.println(map.size());
    }
}
