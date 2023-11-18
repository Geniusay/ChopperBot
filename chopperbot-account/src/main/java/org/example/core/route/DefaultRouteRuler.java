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

        String[] routes = route.split("\\.");
        String[] channelRoutes = channelRoute.split("\\.");

        String label = routes[0];
        String anchor = routes[1];
        String plat = routes[2];

        if(label.isEmpty()||anchor.isEmpty()||plat.isEmpty()){
            return false;
        }

        for (String s : channelRoutes) {
            if(s.equals(label)||("\\*").equals(label)){
                continue;
            }
            return false;
        }
        return true;
    }
}
