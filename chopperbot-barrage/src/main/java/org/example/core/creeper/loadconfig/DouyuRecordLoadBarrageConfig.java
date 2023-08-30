package org.example.core.creeper.loadconfig;

import lombok.Data;
import org.example.constpool.CreeperModuleConstPool;
import org.example.core.creeper.loadtask.DouyuBarrageRecordLoadTask;
import org.example.core.manager.annotation.Creeper;

/**
 * (斗鱼录播)配置信息
 * @author 燧枫
 * @date 2023/4/23 16:30
*/
@Data
@Creeper(creeperName = "douyu_record_barrage",loadTask = DouyuBarrageRecordLoadTask.class,creeperDescription = "斗鱼录播弹幕爬虫")
public class DouyuRecordLoadBarrageConfig extends LoadBarrageConfig {

    // 录播vid
    private String vid;


    public DouyuRecordLoadBarrageConfig(String anchorName, String vid) {
        super(CreeperModuleConstPool.DOUYU, CreeperModuleConstPool.ACTION_RECORD, anchorName);
        this.vid = vid;
        this.url = "https://v.douyu.com/wgapi/vod/center/getBarrageListByPage?vid="+vid;
    }

    @Override
    public String getTaskId() {
        return "douyu_"+anchorName+"_record_"+vid+"_"+startTime;
    }
}
