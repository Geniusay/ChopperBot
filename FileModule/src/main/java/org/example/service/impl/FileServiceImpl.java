package org.example.service.impl;

import org.example.cache.FileCache;
import org.example.cache.FileCacheManager;
import org.example.cache.FileCacheManagerInstance;
import org.example.common.ConfigFile;
import org.example.common.FileType;
import org.example.pojo.vo.ConfigVO;
import org.example.service.FileService;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Genius
 * @date 2023/04/26 01:01
 **/

@Service
public class FileServiceImpl implements FileService {

    @Override
    public List<String> getAllModule() {
        List<String> moduleList = new ArrayList<>();
        for (FileType value : FileType.values()) {
            if(!value.equals(FileType.COMMON)){
                moduleList.add(value.getName());
            }
        }
        return moduleList;
    }

    @Override
    public List<ConfigVO> getAllConfigs() {
        List<ConfigVO> configVOList = new ArrayList<>();
        List<FileCache> runnableFileCaches = FileCacheManagerInstance.getInstance().getRunnableFileCaches();
        for (FileCache runnableFileCache : runnableFileCaches) {
            if (runnableFileCache.getFileType().equals(FileType.COMMON)) {
                continue;
            }
            configVOList.add(new ConfigVO(runnableFileCache.getFileName()
                    ,runnableFileCache.getFullFilePath()
                    ,runnableFileCache.getFileType().getName()));
        }
        return configVOList;
    }
}
