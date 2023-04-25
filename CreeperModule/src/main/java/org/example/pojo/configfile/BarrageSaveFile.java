package org.example.pojo.configfile;

import org.example.common.ConfigFile;
import org.example.constpool.GlobalFileCache;
import org.example.exception.FileCacheException;
import org.example.pojo.Barrage;
import org.example.pojo.download.LoadConfig;
import org.example.util.FileUtil;
import org.example.util.JsonFileUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;

import static org.example.constpool.ConstPool.BARRAGE_ROOT;

/**
 * @author Genius
 * @date 2023/04/25 22:08
 **/

/**
 * 弹幕保存文件配置类，通过LoadConfig获取主播信息，平台信息，弹幕爬取时间，生成弹幕文件
 */
public class BarrageSaveFile extends ConfigFile<ConcurrentLinkedQueue<Barrage>> {
    private LoadConfig loadConfig;
    public BarrageSaveFile(LoadConfig loadConfig,ConcurrentLinkedQueue<Barrage> data) throws FileCacheException {
        super();
        this.loadConfig = loadConfig;
        if (!init(data)) {
            throw new FileCacheException("File init Error");
        }
    }

    /**
     * 自动生成主播弹幕文件夹以及当天直播弹幕数据文本
     * @param data
     * @return
     */
    private boolean init(ConcurrentLinkedQueue<Barrage> data) {
        String fileName = this.filaName();
        setFileName(fileName);
        String rootPath = Paths.get( BARRAGE_ROOT,loadConfig.getAnchorName()).toString(); //获取当前主播的文件夹路径
        setFilePath(rootPath);

        //TODO 待移除 建立主播文件夹
        try {
            Files.createFile(Path.of(rootPath));
        } catch (IOException e) {
            return false;
        }

        setData(data);
        if (!FileUtil.isFileExist(Paths.get(rootPath,fileName).toString())) {

            return !(JsonFileUtil.writeJsonFile(rootPath,fileName,this.packageConfig())==null);
        }
        return true;
    }

    private String filaName(){
        String format = "%s_%s_%s.json";
        return String.format(format,loadConfig.getPlatform(),loadConfig.getAnchorName(),loadConfig.getStartTime());
    }

}
