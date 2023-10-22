package org.example.api;

import org.example.constpool.GlobalFileCache;
import org.example.constpool.ModuleName;
import org.example.init.InitPluginRegister;
import org.example.pojo.vo.FileVO;
import org.example.util.FileUtil;
import org.springframework.stereotype.Component;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Genius
 * @date 2023/10/20 23:51
 **/
@Component
public class SystemFileApi {

    public List<FileVO> videoFileList(){
        String src = GlobalFileCache.ModuleSrcConfigFile.get("src", ModuleName.LIVE).toString();
        List<FileVO> fileVOS = FileUtil.listFiles(src, new ArrayList<>());
        return fileVOS.stream().filter(fileVO -> {
            return fileVO.getFileName().endsWith(".flv");
        }).collect(Collectors.toList());
    }

    public List<FileVO> barrageFileList(){
        String src = GlobalFileCache.ModuleSrcConfigFile.get("src", ModuleName.BARRAGE).toString();
        List<FileVO> fileVOS = new ArrayList<>();
        fileVOS.addAll(FileUtil.listFiles(Path.of(src,"online").toString(), new ArrayList<>()));
        fileVOS.addAll(FileUtil.listFiles(Path.of(src,"record").toString(), new ArrayList<>()));
        return fileVOS;
    }

    public List<FileVO> logFileList(){
        String src = GlobalFileCache.ModuleSrcConfigFile.get("src", ModuleName.CREEPER).toString();
        return FileUtil.listFiles(Path.of(src, "log").toString(), new ArrayList<>());
    }

    public boolean deleteFile(String url){
        return FileUtil.deleteFile(url);
    }

}
