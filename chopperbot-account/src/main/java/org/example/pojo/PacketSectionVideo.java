package org.example.pojo;

import lombok.Data;
import org.example.bean.section.PackageSection;

/**
 * @Description
 * @Author welsir
 * @Date 2024/5/17 14:27
 */

@Data
public class PacketSectionVideo extends PackageSection {

    private String id;
    private boolean priority;
    private boolean isRelay;
    private boolean isAuto = true;
    private boolean isFinish;
}
