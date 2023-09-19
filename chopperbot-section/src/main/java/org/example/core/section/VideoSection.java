package org.example.core.section;

import lombok.Data;

/**
 * @author Genius
 * @date 2023/09/19 19:39
 **/
@Data
public class VideoSection {
    private String videoPath;
    private String tag;
    private String liver;

    private String platform;

    public VideoSection(String videoPath, String tag, String liver, String platform) {
        this.videoPath = videoPath;
        this.tag = tag;
        this.liver = liver;
        this.platform = platform;
    }
}
