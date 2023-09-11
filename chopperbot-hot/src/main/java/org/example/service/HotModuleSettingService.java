package org.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.bean.HotModuleSetting;

import java.util.List;

public interface HotModuleSettingService extends IService<HotModuleSetting> {
    List<HotModuleSetting> getAllSetting();
    boolean updateSetting(HotModuleSetting setting);
}
