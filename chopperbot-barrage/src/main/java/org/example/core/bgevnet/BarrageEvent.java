package org.example.core.bgevnet;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.bean.Barrage;
import org.example.constpool.BarrageModuleConstPool;
import org.example.constpool.FileNameBuilder;
import org.example.core.creeper.file.BarrageSaveFile;
import org.example.core.creeper.loadconfig.LoadBarrageConfig;
import org.example.util.FileUtil;
import org.example.util.JsonFileUtil;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Genius
 * @date 2023/09/13 18:21
 **/
@Data
public class BarrageEvent {
    private String platform;

    private String action;
    private String liver;
    private String date;

    private String fileName;
    private String suffix;
    private List<Barrage> barrages;
    private boolean isSort = false;

    public BarrageEvent(String platform, String action, String liver,  String date) {
        this.action = action;
        this.platform = platform;
        this.liver = liver;
        this.date = date;
        this.fileName = Paths.get(FileNameBuilder.buildBarrageFileName(liver,date)).toString();
    }

    public BarrageEvent(String platform, String action, String liver, String date, List<Barrage> barrages) {
        this.action = action;
        this.platform = platform;
        this.liver = liver;
        this.date = date;
        this.barrages = barrages;
        this.fileName = Paths.get(FileNameBuilder.buildBarrageFileName(liver,date)).toString();
    }

    public BarrageEvent(String platform, String action, String liver, String date, String fileName) {
        this.platform = platform;
        this.action = action;
        this.liver = liver;
        this.date = date;
        this.fileName = fileName;
    }

    public String getBarrageFilePath(){
        return Paths.get(BarrageSaveFile.fileRoot(action,platform), fileName).toString();
    }

    public List<Barrage> getBarrages() {
        if(barrages==null){
            try {
                JSONObject jsonObject = JsonFileUtil.readJsonFileToJSONObject(getBarrageFilePath());
                JSONArray data = jsonObject.getJSONArray("data");
                this.barrages = data.toJavaList(Barrage.class);
            }catch (Exception e){
                return null;
            }
        }
        if(!isSort){
            this.barrages = this.barrages.stream().sorted(Comparator.comparing(Barrage::getTimeReal)).collect(Collectors.toList());
            isSort = true;
        }
        return barrages;
    }
}
