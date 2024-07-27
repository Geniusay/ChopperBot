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
@RequestMapping("/barrage/barrageScoreCurve")
@CheckPlugin(needPlugin = {PluginName.BARRAGE_SCORE_CURVE_PLUGIN})
public class BarrageScoreCurveController {

    @Resource
    BarrageService barrageService;


    @GetMapping("/curveList")
    public Result curveVOList(){
        return Result.success(Map.of("list",barrageService.barrageScoreCurvePluginApi().curveVOList()));
    }

    @GetMapping("/generate")
    public Result generateCurve(@RequestParam(required = false) String filePath,@RequestParam(required = false) String liver){
        if(!StringUtils.hasText(filePath)){
            filePath = "E:\\Project\\ChopperBot\\config\\Barrage\\online\\huya\\Uzi_2023-10-18 21_22_57.json";
            liver = "Uzi";
        }
        BarrageCurveVO barrageCurveVO = barrageService.barrageScoreCurvePluginApi().generateCurve(filePath, liver);
        if(barrageCurveVO==null){
            return Result.error("403","生成失败");
        }
        return Result.success(Map.of("curve",barrageCurveVO));
    }

    @GetMapping("/keywords")
    public Result getKeyWords(@RequestParam(required = false) String liver) {
        return Result.success(Map.of("list", barrageService.barrageScoreCurvePluginApi().getKeyWords(liver)));
    }

    @GetMapping("/delete")
    public Result deleteKeyWords(@RequestParam String liver,@RequestParam String keyWord) {
        return Result.success(Map.of("success",
                barrageService.barrageScoreCurvePluginApi().deleteKeyWord(liver,keyWord)));
    }

    @PostMapping("/add")
    public Result addKeyWords(@RequestBody LiverKeyword keyword) {
        return Result.success(Map.of("success",
                barrageService.barrageScoreCurvePluginApi().addKeyWord(keyword)));
    }

    @PostMapping("/update")
    public Result updateKeyWords(@RequestBody LiverKeyword keyword) {
        return Result.success(Map.of("success",
                barrageService.barrageScoreCurvePluginApi().updateKeyWord(keyword)));
    }

    @GetMapping("/setting")
    public Result getSetting(){
        return Result.success(Map.of("curve",barrageService.barrageScoreCurvePluginApi().getSetting()));
    }
}
