package org.example.core.process;

import org.example.config.BarrageScoreConfig;
import org.example.constpool.BarrageModuleConstPool;
import org.example.constpool.PluginName;
import org.example.core.listen.BarrageFileMonitor;
import org.example.init.InitPluginRegister;
import org.example.pojo.Anchor;
import org.example.pojo.Barrage;
import org.example.util.JsonFileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @Description
 * @Author welsir
 * @Date 2023/8/2 1:25
 */
public class FileConfigProcessor {
    private static final Logger logger = LoggerFactory.getLogger(FileConfigProcessor.class);
    //Obtain the barrage keyword score corresponding to the anchor
    public static void anchorScoreFileLoadingProcessor(Map<String,Map<String,Integer>> anchorMap){
        File anchorFile;
        anchorFile = new File(BarrageModuleConstPool.BARRAGE_SCORE_CONFIG);
        File[] anchors = anchorFile.listFiles();
        if (anchors != null) {
            for (File anchor : anchors) {
                List<Anchor> anchorConfig = JsonFileUtil.readJsonFileToArray(anchor.getAbsolutePath(), Anchor.class);
                anchorMap.put(anchor.getName(),anchorConfig.get(0).getKeywordScore());
            }
        }
        logger.info("anchorScoreFileInitLoading finish ! {}",anchorMap);
    }

    public static void barrageFileLoadingProcessor(Map<String,List<Barrage>> barrageMap){
        File file = new File(BarrageModuleConstPool.BARRAGE_ROOT_PATH);
        for (File listFile : Objects.requireNonNull(file.listFiles())) {
            if(!"barrageScoreConfig".equals(listFile.getName())){
                for (File barrageFile : Objects.requireNonNull(listFile.listFiles())) {
                    List<Barrage> barrageList = JsonFileUtil.readJsonFileToArray(barrageFile.getAbsolutePath(), Barrage.class);
                    barrageMap.put(barrageFile.getName(),barrageList);
                }
            }
        }
        logger.info("barrageFileInitLoading finish ! {}",barrageMap);
    }

    //Batch Edit
    public static void updateBarrageScoreProcessor(List<Anchor> anchorList){

        for (Anchor oneAnchor : anchorList) {
            File anchorFiles = new File(BarrageModuleConstPool.BARRAGE_SCORE_CONFIG);
            String fileName = oneAnchor.getPlathForm()+"-"+oneAnchor.getRoomId();
            for(File anchorFile : Objects.requireNonNull(anchorFiles.listFiles())){
                //Whether to add
                if(fileName.equals(anchorFile.getName())){
                    BarrageFileMonitor plugin= (BarrageFileMonitor) InitPluginRegister.getPlugin(PluginName.BARRAGE_FILE_PLUGIN);
                    Map<String,Map<String,Integer>> anchorBarrageMap = (Map<String,Map<String,Integer>>)plugin.getAnchorBarrageMap();
                    Map<String, Integer> originKeywords = anchorBarrageMap.get(fileName);
                    Map<String, Integer> updateKeywords = oneAnchor.getKeywordScore();
                    Map<String, Integer> mergeMap = mergeAndRemoveDuplicates(originKeywords, updateKeywords);
                    oneAnchor.setKeywordScore(mergeMap);
                    JsonFileUtil.writeJsonFileIsExist(BarrageModuleConstPool.BARRAGE_SCORE_CONFIG+fileName+BarrageModuleConstPool.FILE_TYPE,List.of(oneAnchor));
                    break;
                }
            }
            JsonFileUtil.writeJsonFile(BarrageModuleConstPool.BARRAGE_SCORE_CONFIG,fileName+BarrageModuleConstPool.FILE_TYPE,List.of(oneAnchor));
        }
//        BarrageFileMonitor.sendHotEvent("updateAnchor");
    }
    private static Map<String, Integer> mergeAndRemoveDuplicates(Map<String, Integer> map1, Map<String, Integer> map2) {
        Map<String, Integer> mergedMap = new HashMap<>(map1);
        mergedMap.putAll(map2);
        return mergedMap;
    }

    public static void main(String[] args) {
        Anchor anchor = new Anchor();
        anchor.setNickName("姿态");
        anchor.setPlathForm("虎牙");
        anchor.setRoomId("123456");
        Map<String,Integer> map = new HashMap<>();
        map.put("hahaha",10);
        map.put("人我吃",10);
        anchor.setKeywordScore(map);
        updateBarrageScoreProcessor(List.of(anchor));
    }

}
