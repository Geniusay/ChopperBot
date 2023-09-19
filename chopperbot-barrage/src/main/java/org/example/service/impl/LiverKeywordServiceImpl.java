package org.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.bean.LiverKeyword;
import org.example.mapper.LiverKeywordMapper;
import org.example.service.LiverKeywordService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

import static org.example.constpool.BarrageModuleConstPool.GLOBAL_KEY_WORD;

/**
 * @author Genius
 * @date 2023/09/13 19:21
 **/
@Service
public class LiverKeywordServiceImpl extends ServiceImpl<LiverKeywordMapper, LiverKeyword> implements LiverKeywordService {

    @Resource
    private LiverKeywordMapper mapper;

    @Override
    public List<LiverKeyword> getLiverKeyWords(String liver) {
        return query().eq("liver",liver).list();
    }

    @Override
    public List<LiverKeyword> getGlobalKeyWords() {
        return query().eq("liver",GLOBAL_KEY_WORD).list();
    }

    @Override
    public boolean addKeyWord(LiverKeyword keyword) {
        return mapper.insert(keyword)==1;
    }

    @Override
    public boolean updateKeyWord(LiverKeyword keyword) {
        return update()
                .eq("liver",keyword.getLiver())
                .eq("barrage",keyword.getBarrage())
                .update(keyword);
    }

    @Override
    public boolean deleteKeyWord(String liver, String barrage) {
        return mapper.deleteByMap(Map.of(
                "liver",liver,
                "barrage",barrage
        ))==1;
    }
}
