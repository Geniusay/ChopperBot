package org.example.core.parser;

import org.example.core.creeper.loadconfig.LoadVideoConfig;


/**
 * 全直播平台flv链接解析接口
 * @author 燧枫
 * @date 2023/5/19 17:09
*/
public interface PlatformVideoUrlParser<T extends LoadVideoConfig> {

    String getUrl(T LoadConfig) throws Exception;
}
