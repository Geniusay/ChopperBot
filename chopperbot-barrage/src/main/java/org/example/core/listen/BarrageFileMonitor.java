package org.example.core.listen;

import org.example.constpool.BarrageModuleConstPool;
import org.example.core.process.BarrageScoreProcessor;
import org.example.core.process.FileConfigProcessor;
import org.example.plugin.GuardPlugin;
import org.example.pojo.Barrage;
import org.example.pojo.CreepBarrage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    private BarrageScoreProcessor barrageScoreProcessor;
    private Map<String,List<Barrage>> barrageMap;
    private  Map<String,Map<String,Integer>> anchorMap;
    public BarrageFileMonitor(String module, String pluginName, List<String> needPlugins, boolean isAutoStart) {
        super(module, pluginName, needPlugins, isAutoStart);
    }

    @Override
    public boolean init() {
        logger.info("BarrageModule init..");
        listenChangeEvent = new ArrayBlockingQueue<>(1024);
        anchorMap = new HashMap<>();
        barrageMap = new HashMap<>();
        barrageScoreProcessor = BarrageScoreProcessor.getInstance();
        //Initialize folder
        Path path = Paths.get(BarrageModuleConstPool.BARRAGE_SCORE_CONFIG);
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
        FileConfigProcessor.anchorScoreFileLoadingProcessor(anchorMap);
        FileConfigProcessor.barrageFileLoadingProcessor(barrageMap);
        return super.init();
    }
    @Override
    public void start() {
        //File type    --config
        //               --barrage
        //                 --anchorA
        //                   --fileA
        //                   --fileB
        //                 --anchorB
        //                   --fileA
        //                   --fileB
        try {
            //Receive the barrage file from the crawler engine
            Object res = listenChangeEvent.poll(5, TimeUnit.SECONDS);
            if(null!=res){
                if(res instanceof CreepBarrage){
                    //Create and write to file
                    CreepBarrage creepBarrage = (CreepBarrage) res;
                    String fileName = creepBarrage.getRoomId()+creepBarrage.getAnchorName()+creepBarrage.getCreeperTime();
                    try {
                        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(BarrageModuleConstPool.BARRAGE_ROOT_PATH+fileName+BarrageModuleConstPool.FILE_TYPE));
                        barrageMap.put(fileName,creepBarrage.getBarrageList());
                        bufferedWriter.write(creepBarrage.getBarrageList().toString());
                        logger.info("[BarrageModule]: file write suc!");
                        //传入的参数都必须是唯一标识。目前anchorFileName暂定为房间号 barrageFileName由爬虫模块决定
                        barrageScoreProcessor.calculateBarrageListScore(creepBarrage.getRoomId(),fileName);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }



        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public void send(Object obj){
        listenChangeEvent.offer(obj);
    }

    public Map getAnchorBarrageMap(){
        return anchorMap;
    }
    public Map getBarrageMap(){
        return barrageMap;
    }
}

