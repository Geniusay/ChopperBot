package org.example.BarrageFile;
/**
 * @description : [用于提取config模块的弹幕文件]
 * @author : [Welsir]
 * @createTime : [2023/5/15 22:12]
 */

import com.alibaba.fastjson.JSONArray;
import org.example.entity.Barrage;
import org.example.util.JsonFileUtil;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author welsir
 * @date 2023/5/15 22:12
 */
public class GetFile {

    private static final String barrageFile = "../config/barrage/";

    private static ConcurrentHashMap<String, List<Barrage>> anchorMap = new ConcurrentHashMap<>();

    @Test
    public void getBarrageFile(){
        File rootFile = new File(barrageFile);
        if(rootFile.exists()&&rootFile.isDirectory()){
            classifyAndProcessFolders(rootFile);
        }
        System.out.println(anchorMap.size());
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
                        Map<String, Object> readJsonFile = JsonFileUtil.readJsonFile(jsonFile.getPath());
                        JSONArray data = (JSONArray) readJsonFile.get("data");
                        List<Barrage> barrageList = Barrage.copyProperty(data);
                        anchorMap.put(folderName,barrageList);
                    }
                }
//                anchorMap.put(folderName,barrages);
                // 递归遍历子文件夹
                classifyAndProcessFolders(subFolder);
            }
        }
    }
}
