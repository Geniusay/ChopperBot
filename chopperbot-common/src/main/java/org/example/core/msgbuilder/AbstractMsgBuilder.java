package org.example.core.msgbuilder;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * @Date 2023/10/13
 * @Author xiaochun
 */
public abstract class AbstractMsgBuilder implements MsgBuilder{
    protected Map<String,Object> map;

    public AbstractMsgBuilder() {
        this.map = new HashMap<>();
    }

    @Override
    public AbstractMsgBuilder build(String key, Object data){
        if(data==null){
            return this;
        }
        map.put(key,data);
        return this;
    }

    @Override
    public String done(){
        return JSONObject.toJSONString(map);
    }
}
