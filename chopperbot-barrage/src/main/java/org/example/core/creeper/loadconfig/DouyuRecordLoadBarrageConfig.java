package org.example.core.creeper.loadconfig;

import lombok.Data;
import org.example.constpool.ConstGroup;
import org.example.constpool.ConstPool;
import org.example.constpool.CreeperModuleConstPool;
import org.example.core.creeper.loadtask.DouyuRecordBarrageLoadTask;
import org.example.core.manager.Creeper;

/**
 * (斗鱼录播)配置信息
 * @author 燧枫
 * @date 2023/4/23 16:30
*/
@Data
@Creeper(creeperName = "斗鱼录播爬虫",
        loadTask = DouyuRecordBarrageLoadTask.class,
        creeperDescription = "斗鱼录播弹幕爬虫(WebMagic,需要提供录播Id)",
        priority = 10,
        group = ConstGroup.BARRAGE_RECORD,
        platform = ConstPool.DOUYU
)
public class DouyuRecordLoadBarrageConfig extends LoadBarrageConfig {

    // 录播vid
    private String vid;


    public DouyuRecordLoadBarrageConfig(String anchorName, String vid) {
        super(CreeperModuleConstPool.DOUYU, CreeperModuleConstPool.ACTION_RECORD, anchorName);
        this.vid = vid;
        this.url = "https://v.douyu.com/wgapi/vod/center/getBarrageListByPage?vid="+vid;
    }

}
