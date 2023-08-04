package org.example.taskcenter.handler;

import org.example.bean.Barrage;
import org.example.bean.barrage.DouyuBarrage;
import org.example.bean.live.DouyuLive;
import org.example.core.factory.LoadTaskFactory;
import org.example.pojo.download.assign.DouyuLiveOnlineConfig;
import org.example.pojo.download.assign.DouyuRecordLoadBarrageConfig;
import org.example.taskcenter.request.BarrageReptileRequest;
import org.example.taskcenter.task.ReptileTask;

/**
 * @author Genius
 * @date 2023/08/03 13:35
 **/
public class BarrageTaskHandler implements TaskHandler<BarrageReptileRequest> {


    @Override
    public ReptileTask distribute(BarrageReptileRequest request) {
        Barrage obj = request.getRequestObj();
        ReptileTask task = null;
        if(obj instanceof DouyuBarrage){
            if(request.isOnline()){
               //TODO
            }else{
                task = new ReptileTask(LoadTaskFactory.getLoadTask(new DouyuRecordLoadBarrageConfig(request.getLiver(),request.getRoomId())),request);
            }
        }
        return task;
    }


}
