package org.example.api;

import org.example.bean.GuardVO;
import org.example.bean.HotModuleSetting;
import org.example.core.guard.Guard;
import org.example.core.guard.HotModuleGuard;
import org.example.service.HotModuleSettingService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Genius
 * @date 2023/09/27 21:31
 **/
@Component
public class HotModuleGuardApi {

    @Resource
    private HotModuleSettingService settingService;

    @Resource
    HotModuleGuard hotModuleGuard;

    public List<HotModuleSetting> getAllSettings(){
        return settingService.getAllSetting();
    }

    public boolean changeSetting(HotModuleSetting setting){
        if (settingService.updateSetting(setting)) {
            return hotModuleGuard.addGuard(setting.getPlatform());
        }
        return false;
    }

    public List<GuardVO> getGuards(){
        return hotModuleGuard.getGuards().stream().map(guard->{
            GuardVO guardVO = new GuardVO();
            BeanUtils.copyProperties(guard,guardVO);
            return guardVO;
        }).collect(Collectors.toList());
    }
}
