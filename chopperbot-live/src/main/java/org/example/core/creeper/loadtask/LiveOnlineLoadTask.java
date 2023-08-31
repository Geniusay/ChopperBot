package org.example.core.creeper.loadtask;

import org.example.constpool.PluginName;
import org.example.core.creeper.loadconfig.LoadLiveConfig;
import org.example.core.loadconfig.LoadConfig;
import org.example.core.loadtask.CommonLoadTask;
import org.example.core.manager.LiveDownloadManager;
import org.example.plugin.PluginCheckAndDo;
import org.slf4j.Logger;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author Genius
 * @date 2023/08/31 16:56
 **/
public abstract class LiveOnlineLoadTask extends CommonLoadTask<String> {


    public LiveOnlineLoadTask(LoadConfig loadConfig) {
        super(loadConfig);
    }

    public String start(Logger logger, LoadLiveConfig loadLiveConfig){
        AtomicReference<String> res = new AtomicReference<>();
        PluginCheckAndDo.CheckAndDo(
                (plugin) -> {
                    try {
                        String taskId = ((LiveDownloadManager) plugin).addTask(loadLiveConfig);
                        logger.info("正在爬取{}的直播内容....",loadLiveConfig.getLiverName());
                        if(loadLiveConfig.isShowDownloadTable()){
                            ((LiveDownloadManager) plugin).showDownloadTable(taskId);
                        }
                        res.set((String) ((LiveDownloadManager) plugin).waitResult(taskId, loadLiveConfig));
                    }catch (Exception e){
                        logger.info("爬取{}的直播内容失败!",loadLiveConfig.getLiverName());
                        res.set("");
                    }
                },
                ()->{
                    logger.error("该爬虫需要的{}插件不存在，无法启用直播，请检查插件是否安装", PluginName.LIVE_MANAGER_PLUGIN);
                },
                PluginName.LIVE_MANAGER_PLUGIN
        );
        return res.get();
    }


    @Override
    public void end() {

    }
}
