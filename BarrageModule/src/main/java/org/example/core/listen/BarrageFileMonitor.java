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
import java.nio.file.Path;
import java.nio.file.Paths;
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
    private BlockingQueue<Object> listenChangeEvent;
    private File rootFile;

    private  Map<String,List<Anchor.property>> anchorMap;
    public BarrageFileMonitor(String module, String pluginName, List<String> needPlugins, boolean isAutoStart) {
        super(module, pluginName, needPlugins, isAutoStart);
    }

    @Override
    public boolean init() {
        logger.info("BarrageModule init..");
        rootFile = new File(BarrageModuleConstPool.BARRAGE_FILE_PATH);
        listenChangeEvent = new ArrayBlockingQueue<>(1024);
        anchorMap = new HashMap<>();
        //Initialize folder
        Path path = Paths.get(BarrageModuleConstPool.ANCHOR_FILE_PATH);
        // Determine whether the folder exists, and create it if it does not exist
        if (!Files.exists(path)) {
            try {
                Files.createDirectory(path);
                logger.info("init folder success！");
            } catch (IOException e) {
                logger.info("init folder fail！");
                e.printStackTrace();
            }
        }
        //Read the host configuration file and save it in the map
        //Process streamer configuration files
        processAnchorConfig();
        return super.init();
    }
    @Override
    public void start() {
        //file type    --config
        //               --barrage
        //                 --anchorA
        //                   --fileA
        //                   --fileB
        //                 --anchorB
        //                   --fileA
        //                   --fileB

    }
    public void send(Object obj){
        listenChangeEvent.offer(obj);
    }
    //Obtain the barrage keyword score corresponding to the anchor
    private void processAnchorConfig(){
        File anchorFile;
        anchorFile = new File(BarrageModuleConstPool.ANCHOR_FILE_PATH);
        File[] anchors = anchorFile.listFiles();
        if (anchors != null) {
            for (File anchor : anchors) {
                List<Anchor> anchorConfig = JsonFileUtil.readJsonFileToArray(anchor.getAbsolutePath(), Anchor.class);
                anchorMap.put(anchor.getName(), anchorConfig.get(0).getProperty());
                logger.info("AnchorBarrage::"+anchorMap.toString());
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

    public Map getAnchorBarrageMap(){
        return anchorMap;
    }

}

