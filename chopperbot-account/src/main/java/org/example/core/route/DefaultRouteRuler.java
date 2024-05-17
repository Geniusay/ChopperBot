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

    @Override
    public boolean matchRoute(String route,String channelRoute) {

        if ("*.*.*".equals(channelRoute)) {
            return true;
        }

        String[] routes = route.split("\\.");
        String[] channelRoutes = channelRoute.split("\\.");
        //这里匹配规则换成模糊匹配。只要前两位匹配成功即可
        for (int i = 0; i < 2; i++) {
            // 如果 channelRoute 的这一段不是 "*"，则必须与 route 的相应段相等
            if (!"*".equals(channelRoutes[i]) && !channelRoutes[i].equals(routes[i])) {
                return false;
            }
        }
        return true;
    }
}
