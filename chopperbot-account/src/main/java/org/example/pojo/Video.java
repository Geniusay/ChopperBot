package org.example.pojo;

import lombok.*;
import org.example.bean.section.PackageSection;


/**
 * @Description
 * @Author welsir
 * @Date 2023/9/4 22:08
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Video extends PackageSection {
    private Object message;
    private String videoUrl;
    private String cookies;

}
