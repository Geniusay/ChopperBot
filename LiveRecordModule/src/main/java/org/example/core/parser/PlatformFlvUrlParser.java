package org.example.core.parser;

import org.example.pojo.liveConfig.LiveConfig;

/**
 * 全直播平台flv链接解析接口
 * @author 燧枫
 * @date 2023/5/19 17:09
*/
public interface PlatformFlvUrlParser {

    String getFlvUrl(LiveConfig liveConfig) throws Exception;
}