package org.example.pojo.download.assign;

import lombok.Data;
import org.example.constpool.ConstPool;
import org.example.pojo.download.LoadBarrageConfig;

/**
 * (斗鱼录播)配置信息
 * @author 燧枫
 * @date 2023/4/23 16:30
*/
@Data
public class DouyuRecordLoadBarrageConfig extends LoadBarrageConfig {

    // 录播vid
    private String vid;

    public DouyuRecordLoadBarrageConfig(String anchorName, String vid) {
        super(ConstPool.DOUYU, ConstPool.ACTION_RECORD, anchorName);
        this.vid = vid;
    }
}
