package org.example.config;

import lombok.AllArgsConstructor;
import org.example.bean.ConfigFile;
import org.example.bean.FileType;
import org.example.constpool.CreeperModuleConstPool;

import java.nio.file.Paths;
import java.util.Map;

/**
 * @author Genius
 * @date 2023/07/28 23:54
 **/
public class CreeperConfigFile extends ConfigFile<Map<String,Object>> {

    private static final String filePath = CreeperModuleConstPool.CREEPER_ROOT;

    private static final String fileName = "creeperConfig.json";

    public CreeperConfigFile() {
        super(filePath, fileName,
                Map.of("taskCenter",new TaskCenterConfig(10,50,1000)),
                FileType.CREEPER);
    }



    public static String getFullFilePath(){
        return Paths.get(filePath,fileName).toString();
    }

}
