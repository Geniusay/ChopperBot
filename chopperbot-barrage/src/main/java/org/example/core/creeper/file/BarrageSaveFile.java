package org.example.core.creeper.file;

import com.alibaba.fastjson.JSONArray;
import org.example.bean.Barrage;
import org.example.bean.ConfigFile;
import org.example.core.creeper.loadconfig.LoadBarrageConfig;
import org.example.exception.FileCacheException;
import org.example.util.FileUtil;
import org.example.util.JsonFileUtil;
import org.example.util.TimeUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author Genius
 * @date 2023/04/25 22:08
 **/

/**
 * 弹幕保存文件配置类，通过LoadConfig获取主播信息，平台信息，弹幕爬取时间，生成弹幕文件
 */
public class BarrageSaveFile<T extends Barrage> extends ConfigFile<ConcurrentLinkedQueue<T>> {

    private LoadBarrageConfig loadBarrageConfig;


    private int alreadyRead = 0;

    public BarrageSaveFile(LoadBarrageConfig loadBarrageConfig, ConcurrentLinkedQueue<T> data) throws FileCacheException {
        super();
        this.loadBarrageConfig = loadBarrageConfig;
        if (!init(data)) {
            throw new FileCacheException("File init Error");
        }
    }

    /**
     * 自动生成主播弹幕文件夹以及当天直播弹幕数据文本
     *
     * @param data
     * @return
     */
    private boolean init(ConcurrentLinkedQueue<T> data) {
        String fileName = this.filaName();
        setFileName(fileName);
        //String rootPath = Paths.get(BARRAGE_ROOT, "anchor",loadBarrageConfig.getAnchorName()).toString(); //获取当前主播的文件夹路径
        String rootPath = Paths.get("./config/Barrage", "anchor",loadBarrageConfig.getAnchorName()).toString(); //获取当前主播的文件夹路径
        setFilePath(rootPath);
        Path path = Path.of(rootPath);
        //TODO 待移除 建立主播文件夹
        try {
            if (!Files.exists(path)) {
                Files.createDirectory(path);
            }

        } catch (IOException e) {
            return false;
        }

        setData(data);
        if (!FileUtil.isFileExist(Paths.get(rootPath, fileName).toString())) {

            return !(JsonFileUtil.writeJsonFile(rootPath, fileName, this.packageConfig()) == null);
        }else{
            String filePath = Path.of(rootPath.toString(),fileName).toString();
            Map<String, Object> stringObjectMap = JsonFileUtil.readJsonFile(filePath);
            JSONArray array = (JSONArray) stringObjectMap.get("data");
            for (Object barrage : array) {
                if(barrage instanceof Barrage){
                    data.add((T) barrage);
                    alreadyRead++;
                }
            }
        }

        return true;
    }

    private String filaName() {
        String format = "%s_%s_%s_%s.json";
        return String.format(format,
                loadBarrageConfig.getPlatform(),
                loadBarrageConfig.getAnchorName(),
                loadBarrageConfig.getAction(),
                TimeUtil.getToday_YMD());
    }

    public int getAlreadyRead() {
        return alreadyRead;
    }
}
