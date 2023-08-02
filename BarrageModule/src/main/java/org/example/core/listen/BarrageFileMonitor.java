package org.example.core.listen;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.FileUtils;
import org.example.config.BarrageFileConfig;
import org.example.constpool.BarrageModuleConstPool;
import org.example.plugin.GuardPlugin;
import org.example.pojo.Anchor;
import org.example.pojo.Barrage;
import org.example.thread.ChopperBotGuardianTask;
import org.example.util.FileUtil;
import org.example.util.JsonFileUtil;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @Description heartbeat event
 * @Author welsir
 * @Date 2023/8/1 12:44
 */
public class BarrageFileMonitor extends GuardPlugin {

    private static final Logger logger = LoggerFactory.getLogger(BarrageFileMonitor.class);


    private static BlockingQueue<String> listenChangeEvent;

    private File rootFile;

    private static Map<String,List<Anchor.property>> anchorMap;
    public BarrageFileMonitor(String module, String pluginName, List<String> needPlugins, boolean isAutoStart) {
        super(module, pluginName, needPlugins, isAutoStart);
    }

    @Override
    public boolean init() {
        logger.info("BarrageModule init..");
        rootFile = new File(BarrageModuleConstPool.BARRAGE_FILE_PATH);
        listenChangeEvent = new ArrayBlockingQueue<>(1024);
        anchorMap = new HashMap<>();
        //Read the host configuration file and save it in the map
        //Process streamer configuration files
        processAnchorConfig();
        return super.init();
    }
    @Override
    public void start() {
        try {
            //file type    --config
            //               --barrage
            //                 --anchorA
            //                   --fileA
            //                   --fileB
            //                 --anchorB
            //                   --fileA
            //                   --fileB
            if("creepSuccess".equals(listenChangeEvent.poll(5, TimeUnit.SECONDS))){
                //get barrage file
                Map map = BarrageFileConfig.getBarrageFile();
                File[] files = rootFile.listFiles();
                for (File anchorFile : files) {
                    if (anchorFile.isDirectory()&&anchorFile.length()>0) {
                        for (File barrageFile : Objects.requireNonNull(anchorFile.listFiles())) {
                            Object obj = map.get(anchorFile.getName() + "-" + barrageFile.getName());
                            //If the map key cannot be obtained,then write to map
                            if (obj == null) {
                                try {
                                    String fileJson = Files.readString(barrageFile.toPath());
                                    JSONObject jsonObject = JSON.parseObject(fileJson);
                                    JSONArray data = jsonObject.getJSONArray("data");
                                    map.put(anchorFile.getName() + "-" + barrageFile.getName(), Barrage.copyProperty(data));
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                        }
                    }
                    //update map
                    BarrageFileConfig.setMap(map);
                }
            }
            if("updateAnchor".equals(listenChangeEvent.poll(5,TimeUnit.SECONDS))){
                processAnchorConfig();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void sendHotEvent(String msg){
        listenChangeEvent.offer(msg);
    }

    //Obtain the barrage keyword score corresponding to the anchor
    private static void processAnchorConfig(){
        anchorMap = new HashMap<>();
        File anchorFile;
        anchorFile = new File(BarrageModuleConstPool.ANCHOR_FILE_PATH);
        File[] anchors = anchorFile.listFiles();
        if (anchors != null) {
            for (File anchor : anchors) {
                List<Anchor> anchorConfig = JsonFileUtil.readJsonFileToArray(anchor.getAbsolutePath(), Anchor.class);
                anchorMap.put(anchor.getName(), anchorConfig.get(0).getProperty());
            }
        }
    }

    private static String JSONFileToString(String filePath){
        String fileString = "";
        try (InputStream inputStream = FileUtil.class.getResourceAsStream(filePath);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                fileString += line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileString;
    }

    public static Map getAnchorBarrageMap(){
        return anchorMap;
    }
    public static void main(String[] args) throws InterruptedException {
        while (true){
            processAnchorConfig();
            Thread.sleep(5000);
        }
    }

}
