import request from '@/utils/request';
import {Setting} from "@/views/app/hot/hot_guard/hotSettingTypes";

export function getHotGuardSetting() {
  return request({
    url: '/hot/hotGuard/setting',
    method: 'get',
    params: {
    }
  });
}

export function updateHotGuardSetting(setting:Setting) {
  return request.post('/hot/hotGuard/update',setting)
}

export function guards() {
  return request({
    url: '/hot/hotGuard/guard',
    method: 'get',
    params: {
    }
  });
}

