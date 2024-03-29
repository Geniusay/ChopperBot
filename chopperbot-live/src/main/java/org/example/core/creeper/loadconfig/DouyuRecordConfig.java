package org.example.core.creeper.loadconfig;

import lombok.Data;
import org.example.constpool.ConstGroup;
import org.example.constpool.ConstPool;
import org.example.core.creeper.loadtask.DouyuRecordLoadTask;
import org.example.core.manager.Creeper;

/**
 * @author Genius
 * @date 2023/08/30 16:38
 **/

@Data
@Creeper(creeperName = "斗鱼录播爬取",
        loadTask = DouyuRecordLoadTask.class,
        creeperDescription = "斗鱼录播爬取(采用ffmpeg爬取，需要拥有ffmpeg才能运行)",
        priority = 10,
        group = ConstGroup.LIVE_RECORD,
        platform = ConstPool.DOUYU
)
public class DouyuRecordConfig extends LoadRecordConfig{

    private String vid;

    private String videoStartTime =  "00:00:00";

    private String videoEndTime;


    public DouyuRecordConfig(String downloadPath, String fileName,String vid,long startTime,long endTime) {
        super(downloadPath, fileName, "https://play-tx-recpub.douyucdn2.cn/live/");
        this.vid = vid;
        this.videoStartTime = convertSecondsToTime(startTime);
        this.videoEndTime = convertSecondsToTime(endTime);
    }


    public DouyuRecordConfig(String downloadPath, String fileName,String vid,long endTime){
        super(downloadPath, fileName, "https://play-tx-recpub.douyucdn2.cn/live/");
        this.vid = vid;
        this.videoEndTime = convertSecondsToTime(endTime);
    }

    public String convertSecondsToTime(long seconds) {
        long hours = seconds / 3600;
        long minutes = (seconds % 3600) / 60;
        long remainingSeconds = seconds % 60;

        return String.format("%02d:%02d:%02d", hours, minutes, remainingSeconds);
    }

    public String getVid() {
        return vid;
    }
}
