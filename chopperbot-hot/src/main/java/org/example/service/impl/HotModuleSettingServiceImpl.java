package org.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.bean.FocusLiver;
import org.example.bean.HotModuleSetting;
import org.example.mapper.FocusLiverMapper;
import org.example.mapper.HotModuleSettingMapper;
import org.example.service.FocusLiverService;
import org.example.service.HotModuleSettingService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Genius
 * @date 2023/09/10 22:24
 **/
@Service
public class HotModuleSettingServiceImpl extends ServiceImpl<HotModuleSettingMapper, HotModuleSetting> implements HotModuleSettingService {
    @Override
    public List<HotModuleSetting> getAllSetting() {
        return query().list();
    }

    @Override
    public boolean updateSetting(HotModuleSetting setting) {
        return update().eq("platform",setting.getPlatform()).update();
    }
}
