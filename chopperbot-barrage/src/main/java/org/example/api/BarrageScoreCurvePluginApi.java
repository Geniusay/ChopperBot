package org.example.api;

import org.example.bean.BarrageCurveVO;
import org.example.bean.LiverKeyword;
import org.example.config.BarrageModuleConfig;
import org.example.core.bgevnet.BarrageEvent;
import org.example.core.bgevnet.bgscore.BarragePoint;
import org.example.core.bgevnet.bgscore.BarrageScoreCurvePlugin;
import org.example.service.LiverKeywordService;
import org.example.util.ConfigFileUtil;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Genius
 * @date 2023/10/13 16:45
 **/
@Component
public class BarrageScoreCurvePluginApi {

    @Resource
    private BarrageScoreCurvePlugin barrageScoreCurvePlugin;

    @Resource
    private LiverKeywordService keywordService;

    public List<BarrageCurveVO> curveVOList(){
        List<BarrageCurveVO> curveVOList = new ArrayList<>();
        barrageScoreCurvePlugin.getBarragePointMap().forEach(
                (k,v)->{
                    curveVOList.add(new BarrageCurveVO(k,v));
                }
        );
        return curveVOList;
    }

    public BarrageCurveVO generateCurve(String filePath,String liver){
        BarrageEvent event = new BarrageEvent();
        event.setFileName(filePath);
        event.setLiver(liver);
        List<BarragePoint> points = barrageScoreCurvePlugin.generateCurve(event);
        return new BarrageCurveVO(filePath,points);
    }

    public List<LiverKeyword> getKeyWords(String anchor){
        return barrageScoreCurvePlugin.getKetWords(anchor);
    }

    public List<LiverKeyword> getKeyWords(){
        return keywordService.getGlobalKeyWords();
    }

    public boolean updateKeyWord(LiverKeyword keyword){
        return keywordService.updateKeyWord(keyword);
    }

    public boolean addKeyWord(LiverKeyword keyword){
        return keywordService.addKeyWord(keyword);
    }

    public boolean deleteKeyWord(String anchor,String keyword){
        return keywordService.deleteKeyWord(anchor,keyword);
    }

    public void changeSetting(Map<String,Object> settings){
       ConfigFileUtil.changeSetting(settings,BarrageModuleConfig.getFullFilePath(),"barrageScoreCurve");
    }

    public Object getSetting(){
        return ConfigFileUtil.getSetting(BarrageModuleConfig.getFullFilePath(),"barrageScoreCurve");
    }
}
