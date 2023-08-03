package org.example.taskcenter.request;

import org.example.bean.Barrage;

import java.util.function.Consumer;

/**
 * @author Genius
 * @date 2023/07/28 23:06
 **/

//TODO 弹幕请求待完善
public class BarrageReptileRequest extends ReptileRequest<Barrage>{

    public BarrageReptileRequest(Barrage requestObj, Consumer callback) {
        super(requestObj, callback);
    }
}
