package org.example.core.section;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.example.bean.Barrage;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Genius
 * @date 2023/09/19 19:39
 **/
@Data
@TableName("section_parking")
public class VideoSection {
    private String videoPath;
    private String tag;
    private String liver;

    private List<Barrage> barrages;
    private String platform;

    public VideoSection(String videoPath, String tag, String liver, String platform) {
        this.videoPath = videoPath;
        this.tag = tag;
        this.liver = liver;
        this.platform = platform;
        barrages = new ArrayList<>();
    }
}
