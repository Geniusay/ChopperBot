package org.example.api;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.example.core.gpt.OpenAPIPlugin;
import org.example.mapper.GPTKeyMapper;
import org.example.pojo.GPTKey;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @Date 2023/10/12
 * @Author xiaochun
 */
@Component
public class OpenAPIPluginApi {
    @Resource
    private OpenAPIPlugin plugin;

    public boolean changeKey(GPTKey newKey){
        return plugin.getMapper().update(newKey, new QueryWrapper<GPTKey>().eq("function", newKey.getFunction())) == 1;
    }

    public boolean deleteKey(String function){
        return plugin.getMapper().deleteByMap(Map.of("function",function))==1;
    }
    public boolean addKey(GPTKey key){
        return plugin.getMapper().insert(key)==1;
    }

    public List<GPTKey> getKeys(){
        return plugin.getMapper().selectList(new QueryWrapper<>());
    }

    public List<String> functions(){
        return plugin.funcList();
    }
}
