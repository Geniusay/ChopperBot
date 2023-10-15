package org.example.util;

import java.io.File;
import java.nio.file.Path;

/**
 * @Description
 * @Author welsir
 * @Date 2023/10/15 20:43
 */
public class GetScriptPath {

    public static Path getScriptPath(String path){
        // 构建绝对路径
        File scriptFile = new File(path);
        return Path.of(scriptFile.getAbsolutePath());
    }

}
