package org.example.bean.section;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Genius
 * @date 2023/09/19 19:39
 **/
@Data
@TableName("section_parking")
@NoArgsConstructor
@AllArgsConstructor
public class VideoSection {
    private String videoPath;
    private String tag;
    private String liver;

    private String barrageFile;
    @TableField(exist = false)
    private List<String> barrages;
    private String platform;

    public VideoSection(String videoPath, String tag, String liver, String platform) {
        this.videoPath = videoPath;
        this.tag = tag;
        this.liver = liver;
        this.platform = platform;
        barrages = new ArrayList<>();
    }
}
