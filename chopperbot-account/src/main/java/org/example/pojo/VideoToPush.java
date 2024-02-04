package org.example.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description
 * @Author welsir
 * @Date 2023/10/13 19:02
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VideoToPush {

    private String videoPath;
    private String cookies;
    private PlatformType platform;
    private String coverPath;
    private String devicePath;
    private String tag;
    private String title;
    private String type;

}
