package org.example.core.factory.videoPushFactory;

import org.example.bean.section.PackageSection;
import org.example.pojo.PacketSectionVideo;

import java.util.List;

/**
 * @Description
 * @Author welsir
 * @Date 2024/5/17 12:24
 */
public interface VideoPushStrategy {

    PacketSectionVideo wrapperSectionVideo(PackageSection obj);

    List<String> queryPriority();

    void changePriority(List<String> s);
}
