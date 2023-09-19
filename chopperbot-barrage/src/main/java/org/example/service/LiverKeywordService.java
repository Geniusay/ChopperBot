package org.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.bean.LiverKeyword;

import java.util.List;

public interface LiverKeywordService extends IService<LiverKeyword> {

    List<LiverKeyword> getLiverKeyWords(String liver);

    List<LiverKeyword> getGlobalKeyWords();

    boolean addKeyWord(LiverKeyword keyword);

    boolean updateKeyWord(LiverKeyword keyword);

    boolean deleteKeyWord(String liver,String barrage);
}
