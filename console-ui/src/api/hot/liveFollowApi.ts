import request from '@/utils/request';
import {FocusLiver} from "@/views/app/hot/live_follow/focusLiverTypes";
export function followList() {
  return request({
    url: '/hot/liveFollow/list',
    method: 'get',
    params: {
    }
  });
}

export function addFollow(liver:FocusLiver){
  return request({
    url: '/hot/liveFollow/add',
    method: 'post',
    params: {
      liver
    }
  });
}

export function unFollow(platform:string,liver:string){
  return request({
    url: '/hot/liveFollow/delete',
    method: 'Get',
    params: {
      platform:platform,
      liver:liver
    }
  });
}
