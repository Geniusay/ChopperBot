import request from '@/utils/request';

export function getHotLive(platform:string) {
  return request({
    url: '/hot/hotLive/live',
    method: 'get',
    params: {
      platform
    }
  });
}

export function getHotModule(platform:string) {
  return request({
    url: '/hot/hotLive/module',
    method: 'get',
    params: {
      platform
    }
  });
}

export function getModuleLive(moduleId:string,platform:string) {
  return request({
    url: '/hot/hotLive/modelLive',
    method: 'get',
    params: {
      moduleId,
      platform
    }
  });
}
