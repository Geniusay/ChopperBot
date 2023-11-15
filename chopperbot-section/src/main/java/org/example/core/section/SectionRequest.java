package org.example.core.section;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Genius
 * @date 2023/09/19 19:37
 **/
@Data
public class SectionRequest {

    private String action;
    private String videoName;
    private long startTime;
    private long endTime;

    private List<String> barrages;
    private String platform;

    private String liver;

    private String tag;

    private String date;
    public SectionRequest(String videoName,String action, long startTime, long endTime,  String liver,String platform,String date) {
        this.videoName = videoName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.liver = liver;
        this.action = action;
        this.platform = platform;
        this.date = date;
        this.barrages = new ArrayList<>();
    }
}
