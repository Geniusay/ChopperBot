package org.example.pojo;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.bean.section.PackageSection;

/**
 * @Description
 * @Author welsir
 * @Date 2023/10/13 19:02
 */

@EqualsAndHashCode(callSuper = true)
@Data
public class VideoToPublish extends PackageSection {

    private String cookies;
    private String title;
    private String devicePath;

}
