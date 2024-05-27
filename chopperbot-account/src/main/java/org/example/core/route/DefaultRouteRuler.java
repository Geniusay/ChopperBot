package org.example.core.route;

import org.apache.ibatis.plugin.Plugin;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Author welsir
 * @Date 2023/11/18 19:52
 */
@Component
public class DefaultRouteRuler extends AbstractRouteRuler{

    //todo: 需要重构规则匹配
    //目前的匹配规则是：管道依据 '.' 来区分字段 例如 “平台A.主播B.游戏C”
    //未来可能会支持运算符 '&&'&'||'
    @Override
    public boolean matchRoute(String route,String channelRoute) {

        if ("*.*.*".equals(channelRoute)) {
            return true;
        }

        String[] routes = route.split("\\.");

        String[] channelRoutes = channelRoute.split("\\.");
        int channelLen = channelRoutes.length;
        for(String s:channelRoutes){
            for(String r:routes){
                if(s.equals(r)){
                    channelLen--;
                }
            }
        }
        return channelLen == 0;
    }
}
