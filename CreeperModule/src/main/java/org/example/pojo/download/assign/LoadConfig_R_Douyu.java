package org.example.pojo.download.assign;

import lombok.Data;
import org.example.constpool.ConstPool;
import org.example.pojo.download.LoadConfig;
import org.springframework.stereotype.Component;

/**
 * (斗鱼录播)配置信息
 * @author 燧枫
 * @date 2023/4/23 16:30
*/
@Data
public class LoadConfig_R_Douyu extends LoadConfig {

    // 录播vid
    private String vid;

    public LoadConfig_R_Douyu(String anchorName, String vid) {
        super(ConstPool.DOUYU, ConstPool.ACTION_RECORD, anchorName);
        this.vid = vid;
    }
}
