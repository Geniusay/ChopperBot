package org.example.config;

import org.example.bean.ConfigFile;
import org.example.constpool.BarrageModuleConstPool;
import org.example.constpool.PluginName;

import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

/**
 * @author Genius
 * @date 2023/09/13 19:12
 **/
public class BarrageModuleConfig extends ConfigFile<Map<String,Object>> {

    private static final String fileName = "barrageModuleConfig.json";

    public BarrageModuleConfig(String module, String pluginName, List<String> needPlugins, boolean isAutoStart) {
        super(module, pluginName, needPlugins, isAutoStart
                , BarrageModuleConstPool.BARRAGE_ROOT_PATH,fileName,
                Map.of(
                        "barrageScoreCurve",Map.of(
                                "duration",5000,
                                "basicBarrageScore",5,
                                "scoreStrategy","SCORING",
                                "splitStrategy","ORDER"
                        ),
                        "popularRange",Map.of(

                        ),
                        "InstantSlicing",Map.of("handler","ScheduleTime",
                                "ScheduleTime",Map.of("duration",30*60*1000)
                        )
                ))
        ;
    }

    public static String getFullFilePath(){
        return Paths.get(BarrageModuleConstPool.BARRAGE_ROOT_PATH,fileName).toString();
    }
}
