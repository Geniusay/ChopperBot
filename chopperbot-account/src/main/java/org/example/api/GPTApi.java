package org.example.api;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.example.core.gpt.ChatGPTPlugin;
import org.example.mapper.GPTKeyMapper;
import org.example.pojo.GPTKey;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Date 2023/10/12
 * @Author xiaochun
 */
@Component
public class GPTApi {
    @Resource
    private ChatGPTPlugin plugin;

    @Resource
    private GPTKeyMapper mapper;

    public boolean changeKey(GPTKey newKey){
        String oldKey = plugin.getKey().getKey();
        if (mapper.update(newKey,new QueryWrapper<GPTKey>().eq("key",oldKey))==1) {
            plugin.setKey(newKey);
            return true;
        }
        return false;
    }

    public boolean addKey(GPTKey key){
        if (mapper.selectCount(new QueryWrapper<GPTKey>())==0) {
            return mapper.insert(key)==1;
        }
        return false;
    }

    public GPTKey getKey(){
        return mapper.selectOne(new QueryWrapper<GPTKey>());
    }

}
