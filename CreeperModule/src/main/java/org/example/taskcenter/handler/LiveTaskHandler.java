package org.example.taskcenter.handler;

import org.example.bean.Live;
import org.example.bean.live.DouyuLive;
import org.example.core.factory.LoadTaskFactory;
import org.example.pojo.download.assign.DouyuLiveOnlineConfig;
import org.example.taskcenter.request.LiveReptileRequest;
import org.example.taskcenter.task.ReptileTask;

/**
 * 直播请求分发包装中心
 * @author Genius
 * @date 2023/07/28 17:39
 **/

public class LiveTaskHandler implements TaskHandler<LiveReptileRequest> {

    @Override
    public ReptileTask distribute(LiveReptileRequest request) {
        Live live = request.getRequestObj();
        ReptileTask task = null;
        if(live instanceof DouyuLive){
            if(request.isOnline()){
                task = new ReptileTask(LoadTaskFactory.getLoadTask(new DouyuLiveOnlineConfig((DouyuLive) live)),request);
            }else{
                //Todo task = new ReptileTask(录播LoadTask,this);
            }
        }
        return task;
    }
}
