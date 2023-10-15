package org.example.controller;

import com.genius.assistant.common.Result;
import org.example.bean.BarrageCurveVO;
import org.example.bean.LiverKeyword;
import org.example.constpool.PluginName;
import org.example.plugin.annotation.CheckPlugin;
import org.example.service.BarrageService;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author Genius
 * @date 2023/10/13 17:52
 **/
@RestController
@RequestMapping("/barrage")
public class BarrageController {

    @Resource
    BarrageService barrageService;

    @CheckPlugin(needPlugin = {PluginName.BARRAGE_SCORE_CURVE_PLUGIN})
    @GetMapping("/barrageScoreCurve/curveList")
    public Result curveVOList(){
        return Result.success(Map.of("list",barrageService.barrageScoreCurvePluginApi().curveVOList()));
    }

    @CheckPlugin(needPlugin = {PluginName.BARRAGE_SCORE_CURVE_PLUGIN})
    @GetMapping("/barrageScoreCurve/generate")
    public Result generateCurve(@RequestParam String filePath,@RequestParam(required = false) String liver){
        BarrageCurveVO barrageCurveVO = barrageService.barrageScoreCurvePluginApi().generateCurve(filePath, liver);
        if(barrageCurveVO==null){
            return Result.error("403","生成失败");
        }
        return Result.success(Map.of("curve",barrageCurveVO));
    }

    @CheckPlugin(needPlugin = {PluginName.BARRAGE_SCORE_CURVE_PLUGIN})
    @GetMapping("/barrageScoreCurve/keywords")
    public Result getKeyWords(@RequestParam(required = false) String liver) {
        if (StringUtils.hasText(liver)) {
            return Result.success(Map.of("list", barrageService.barrageScoreCurvePluginApi().getKeyWords(liver)));
        } else {
            return Result.success(Map.of("list", barrageService.barrageScoreCurvePluginApi().getKeyWords()));
        }
    }

    @CheckPlugin(needPlugin = {PluginName.BARRAGE_SCORE_CURVE_PLUGIN})
    @GetMapping("/barrageScoreCurve/delete")
    public Result deleteKeyWords(@RequestParam String liver,@RequestParam String keyWord) {
        return Result.success(Map.of("success",
                barrageService.barrageScoreCurvePluginApi().deleteKeyWord(liver,keyWord)));
    }


    @CheckPlugin(needPlugin = {PluginName.BARRAGE_SCORE_CURVE_PLUGIN})
    @PostMapping("/barrageScoreCurve/add")
    public Result addKeyWords(@RequestBody LiverKeyword keyword) {
        return Result.success(Map.of("success",
                barrageService.barrageScoreCurvePluginApi().addKeyWord(keyword)));
    }

    @CheckPlugin(needPlugin = {PluginName.BARRAGE_SCORE_CURVE_PLUGIN})
    @PostMapping("/barrageScoreCurve/update")
    public Result updateKeyWords(@RequestBody LiverKeyword keyword) {
        return Result.success(Map.of("success",
                barrageService.barrageScoreCurvePluginApi().updateKeyWord(keyword)));
    }

    @CheckPlugin(needPlugin = {PluginName.BARRAGE_SCORE_CURVE_PLUGIN})
    @GetMapping("/barrageScoreCurve/setting")
    public Result getSetting(){
        return Result.success(Map.of("curve",barrageService.barrageScoreCurvePluginApi().getSetting()));
    }
}
