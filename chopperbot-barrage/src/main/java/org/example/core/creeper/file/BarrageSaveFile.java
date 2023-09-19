package org.example.core.creeper.file;

import com.alibaba.fastjson.JSONArray;
import org.example.bean.Barrage;
import org.example.bean.ConfigFile;
import org.example.constpool.ConstPool;
import org.example.constpool.FileNameBuilder;
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

import static org.example.constpool.BarrageModuleConstPool.BARRAGE_ROOT_PATH;

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
        String fileName = fileName(loadBarrageConfig.getAnchorName(),loadBarrageConfig.getStartTime());
        setFileName(fileName);

        String rootPath = fileRoot(loadBarrageConfig.getAction(),loadBarrageConfig.getPlatform());

        setFilePath(rootPath);
        Path path = Path.of(rootPath);
        try {
            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }

        } catch (IOException e) {
            return false;
        }

        setData(data);
        String fileFullPath = Paths.get(rootPath, fileName).toString();
        if (!FileUtil.isFileExist(fileFullPath)) {

            return !(JsonFileUtil.writeJsonFile(rootPath, fileName, this.packageConfig()) == null);
        }else{
            Map<String, Object> stringObjectMap = JsonFileUtil.readJsonFile(fileFullPath);
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

    public static String fileRoot(String action,String platform){
        return Paths.get(BARRAGE_ROOT_PATH, action,platform).toString(); //获取当前主播的文件夹路径
    }

    public static String fileName(String anchorName,String startTime) {
        return FileNameBuilder.buildBarrageFileName(anchorName, startTime);
    }

    public int getAlreadyRead() {
        return alreadyRead;
    }
}
